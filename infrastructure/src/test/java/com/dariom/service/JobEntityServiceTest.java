package com.dariom.service;

import com.dariom.configuration.mapper.JobMapper;
import com.dariom.domain.model.Job;
import com.dariom.persistence.entities.JobEntity;
import com.dariom.persistence.repositories.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobEntityServiceTest {

    @Mock
    private JobMapper jobMapper;

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobEntityService sut;

    private List<Job> jobs;
    private List<JobEntity> jobEntities;

    @BeforeEach
    void setUp() {
        Job job1 = Job.builder().id(1L).title("Software Engineer").description("Develop software").location("New " +
                "York").build();
        Job job2 =
                Job.builder().id(2L).title("Data Scientist").description("Analyze data").location("San Francisco").build();
        jobs = Arrays.asList(job1, job2);

        JobEntity jobEntity1 =
                JobEntity.builder().id(1L).title("Software Engineer").description("Develop software").location("New " +
                "York").build();
        JobEntity jobEntity2 = JobEntity.builder().id(2L).title("Data Scientist").description("Analyze data").location(
                "San Francisco").build();
        jobEntities = Arrays.asList(jobEntity1, jobEntity2);
    }


    @Test
    void getAll() {
        when(jobRepository.findAll()).thenReturn(jobEntities);
        when(jobMapper.toJobs(jobEntities)).thenReturn(jobs);

        List<Job> result = sut.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Software Engineer", result.get(0).getTitle());
        assertEquals("Data Scientist", result.get(1).getTitle());

        verify(jobRepository, times(1)).findAll();
        verify(jobMapper, times(1)).toJobs(jobEntities);
    }
}