package com.dariom.domain.service.impl;

import com.dariom.domain.model.Application;
import com.dariom.domain.model.Job;
import com.dariom.domain.model.JobRetrieveStrategy;
import com.dariom.domain.model.JobStatus;
import com.dariom.domain.service.JobEntityRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JobDomainServiceImplTest {

    @Mock
    private JobEntityRepository jobEntityRepository;

    private JobDomainServiceImpl sut;


    @BeforeEach
    void setUp() {
        sut = new JobDomainServiceImpl(jobEntityRepository);
        Job job1open = Job.builder().id(1).title("Software Engineer")
                .description("Develop software")
                .location("New York")
                .applications(new ArrayList<>(List.of()))
                .publishDate(LocalDateTime.of(2024, 3, 2, 14, 30, 0))
                .status(JobStatus.OPEN)
                .build();
        Job job2open = Job.builder().id(2).title("Scrum Master")
                        .description("You will scrum")
                        .location("San Francisco")
                        .applications(new ArrayList<>(List.of()))
                        .publishDate(LocalDateTime.of(2024, 3, 1, 14, 30, 0))
                        .status(JobStatus.OPEN)
                        .build();
        Job jobWithOneApplication = Job.builder().id(3).title("Data Scientist")
                        .description("Analyze data")
                        .location("San Francisco")
                        .status(JobStatus.OPEN)
                        .publishDate(LocalDateTime.of(2024, 4, 5, 14, 30, 0))
                        .applications(getApplications(1))
                        .build();
        Job jobWithTenApplication = Job.builder().id(4).title("Data Scientist2")
                        .description("Analyze data")
                        .location("San Francisco")
                        .status(JobStatus.OPEN)
                        .publishDate(LocalDateTime.of(2024, 4, 5, 14, 30, 0))
                        .applications(getApplications(10))
                        .build();
        Job job1closed = Job.builder().id(5).title("Manager")
                        .description("You will scrum")
                        .location("San Francisco")
                        .applications(new ArrayList<>(List.of()))
                        .publishDate(LocalDateTime.of(2022, 4, 5, 14, 30, 0))
                        .status(JobStatus.CLOSED)
                        .build();
        when(jobEntityRepository.getAll()).thenReturn(Arrays.asList(job1open, job2open, jobWithOneApplication,
                jobWithTenApplication,job1closed));
    }

    @Test
    void getAllByStrategyIsNotOpen() {
        JobRetrieveStrategy jobRetrieveStrategy = JobRetrieveStrategy.builder().open(false).build();

        List<Job> jobs = sut.getAllByStrategy(jobRetrieveStrategy);

        assertEquals(2, jobs.size());
        assertTrue(jobs.stream().noneMatch(Job::isOpen));
    }

    @Test
    void getAllByStrategyIsOpen() {
        JobRetrieveStrategy jobRetrieveStrategy = JobRetrieveStrategy.builder().open(true).build();

        List<Job> jobs = sut.getAllByStrategy(jobRetrieveStrategy);

        assertEquals(3, jobs.size());
        assertTrue(jobs.stream().allMatch(Job::isOpen));
    }

    @Test
    void getAllByStrategyIsOpenOrderByPublishDate() {
        JobRetrieveStrategy jobRetrieveStrategy =
                JobRetrieveStrategy.builder().open(true)
                        .orderByField("publishDate")
                        .build();

        List<Job> jobs = sut.getAllByStrategy(jobRetrieveStrategy);

        assertEquals(3, jobs.size());
        assertTrue(jobs.stream().allMatch(Job::isOpen));
        Assertions.assertThat(jobs).isSortedAccordingTo(Comparator.comparing(Job::getPublishDate));
    }

    private List<Application> getApplications(int n) {
        List<Application> applications = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            applications.add(Application.builder().id(Long.valueOf(Integer.toString(n))).build());
        }
        return applications;
    }
}