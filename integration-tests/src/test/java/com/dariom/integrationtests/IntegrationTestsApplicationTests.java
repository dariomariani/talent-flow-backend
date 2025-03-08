package com.dariom.integrationtests;

import com.dariom.dto.ApiResponse;
import com.dariom.dto.AuthDto;
import com.dariom.dto.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.jdbc.Sql;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class IntegrationTestsApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	void setup() {
		restTemplate.getRestTemplate().setInterceptors(List.of(logInterceptor()));
	}

	private ClientHttpRequestInterceptor logInterceptor() {
		return (request, body, execution) -> {
			log.trace("Request: {} {}", request.getMethod(), request.getURI());
			log.trace("Headers: {}", request.getHeaders());
			if (body.length > 0) {
				log.trace("Body: {}", new String(body, StandardCharsets.UTF_8));
			}

			ClientHttpResponse response = execution.execute(request, body);

			log.trace("Response Status: {}", response.getStatusCode());
			log.trace("Response Headers: {}", response.getHeaders());

			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))) {
				StringBuilder responseBody = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					responseBody.append(line).append('\n');
				}
				log.trace("Response Body: {}", responseBody);
			}

			return response;
		};
	}

	@Test
	void login() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		LoginDto loginDto = new LoginDto("keith_recruiter@ecorp.com", "12345");
		HttpEntity<LoginDto> request = new HttpEntity<>(loginDto, headers);
		ResponseEntity<ApiResponse<AuthDto>> response = restTemplate.exchange("/auth/login",
				HttpMethod.POST,
				request, new ParameterizedTypeReference<>() {});
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
	}

}
