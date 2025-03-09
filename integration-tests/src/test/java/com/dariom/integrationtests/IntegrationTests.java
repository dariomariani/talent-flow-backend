package com.dariom.integrationtests;

import com.dariom.dto.ApiResponse;
import com.dariom.dto.AuthDto;
import com.dariom.dto.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:application-test.yml")
@ActiveProfiles("test")
@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ComponentScan("com.dariom.configuration.mapper")
@ComponentScan("com.dariom.configuration")
@ComponentScan("com.dariom.filters")
@ComponentScan("com.dariom.controller")
@ComponentScan("com.dariom.service")
@EnableJpaRepositories(basePackages = "com.dariom.persistence.repositories")
@EntityScan(basePackages = "com.dariom.persistence.entities")
class IntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;

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

	@Test
	void getMeUnauthorized() {
		ResponseEntity<String> response = restTemplate.getForEntity("/users/me", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
	}

}
