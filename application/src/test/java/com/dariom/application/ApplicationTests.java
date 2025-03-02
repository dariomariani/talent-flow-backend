package com.dariom.application;

import com.dariom.configuration.RestApiExceptionHandler;
import com.dariom.configuration.mapper.JobDtoMapper;
import com.dariom.controller.JobController;
import com.dariom.domain.model.Job;
import com.dariom.domain.service.JobDomainService;
import com.dariom.dto.JobDto;
import com.dariom.filters.JwtSecurityFilter;
import com.dariom.service.JwtService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {ApplicationTests.class})
@WebMvcTest(controllers = {JobController.class})
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JobDomainService jobDomainService;

    @MockitoBean
    private JobDtoMapper jobDtoMapper;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JobController jobController;

    private List<Job> jobs;
    private List<JobDto> jobDtos;

    @Value("${test.api.jwt-secret}")
    private String jwtSecret;

    @Value("${test.api.test-user}")
    private String testUserName;

    private String jwtToken;

    @BeforeAll
    static void setup() {

    }

    @BeforeEach
    void setUp() {
        when(userDetails.getUsername()).thenReturn(testUserName);
        when(userDetailsService.loadUserByUsername(testUserName)).thenReturn(userDetails);
        Job job1 = Job.builder().id(1).title("Software Engineer").description("Develop software").location("New " +
                "York").build();
        Job job2 =
                Job.builder().id(2).title("Data Scientist").description("Analyze data").location("San Francisco").build();
        jobs = Arrays.asList(job1, job2);

        JobDto jobDto1 = JobDto.builder().id(1).title("Software Engineer").description("Develop software").location(
                "New York").build();
        JobDto jobDto2 =
                JobDto.builder().id(2).title("Data Scientist").description("Analyze data").location("San " +
                        "Francisco").build();
        jobDtos = Arrays.asList(jobDto1, jobDto2);

        JwtService jwtService = new JwtService(jwtSecret, "test_api");
        jwtToken = jwtService.generateToken(userDetails);
        mockMvc =
                MockMvcBuilders.standaloneSetup(jobController).setControllerAdvice(new RestApiExceptionHandler())
                        .addFilter(new JwtSecurityFilter(jwtService, userDetailsService))
                        .build();
    }

    @Test
    void getJobsAuthenticatedCall() throws Exception {
        when(userDetails.getAuthorities()).thenReturn(null);
        when(jobDomainService.getAll()).thenReturn(jobs);
        when(jobDtoMapper.toJobsDto(jobs)).thenReturn(jobDtos);

        mockMvc.perform(MockMvcRequestBuilders.get("/jobs")
                .contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload").isArray())
                .andExpect(jsonPath("$.payload[0].title").value("Software Engineer"))
                .andExpect(jsonPath("$.payload[1].title").value("Data Scientist"));

        verify(jobDomainService, times(1)).getAll();
        verify(jobDtoMapper, times(1)).toJobsDto(jobs);
    }

	@Test
	void getJobsNotAuthenticatedCall() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/jobs")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());

		verify(jobDomainService, times(0)).getAll();
		verify(jobDtoMapper, times(0)).toJobsDto(jobs);
	}

}
