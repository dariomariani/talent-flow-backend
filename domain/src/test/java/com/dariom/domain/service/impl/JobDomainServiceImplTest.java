package com.dariom.domain.service.impl;

import com.dariom.domain.model.Job;
import com.dariom.domain.model.JobStatus;
import com.dariom.domain.service.JobEntityRepository;
import com.dariom.domain.strategy.impl.JobStatusSortedStrategy;
import com.dariom.domain.strategy.impl.JobStatusStrategy;
import com.dariom.domain.strategy.impl.SortedStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobDomainServiceImplTest {

    @Mock
    private JobEntityRepository jobEntityRepository;

    private JobDomainServiceImpl sut;

    private Job job1open;
    private Job job2open;
    private Job job3open;
    private Job job1closed;
    private Job job2closed;


    @BeforeEach
    void setUp() {
        sut = new JobDomainServiceImpl(jobEntityRepository);
        job1open = Job.builder().id(1).title("Software Engineer")
                .description("Develop software")
                .location("New York")
                .applications(new ArrayList<>(List.of()))
                .publishDate(LocalDateTime.of(2024, 3, 2, 14, 30, 0))
                .status(JobStatus.OPEN)
                .build();
        job2open = Job.builder().id(2).title("Scrum Master")
                        .description("You will scrum")
                        .location("San Francisco")
                        .applications(new ArrayList<>(List.of()))
                        .publishDate(LocalDateTime.of(2024, 3, 1, 14, 30, 0))
                        .status(JobStatus.OPEN)
                        .build();
        job3open = Job.builder().id(3).title("Data Scientist")
                        .description("Analyze data")
                        .location("San Francisco")
                        .status(JobStatus.OPEN)
                        .publishDate(LocalDateTime.of(2024, 1, 5, 14, 30, 0))
                        .build();
        job1closed = Job.builder().id(5).title("Manager")
                        .description("You will manage")
                        .location("San Francisco")
                        .applications(new ArrayList<>(List.of()))
                        .publishDate(LocalDateTime.of(2022, 4, 5, 14, 30, 0))
                        .status(JobStatus.CLOSED)
                        .build();
        job2closed = Job.builder().id(5).title("Manager")
                .description("You will manage")
                .location("San Francisco")
                .applications(new ArrayList<>(List.of()))
                .publishDate(LocalDateTime.of(2022, 4, 5, 14, 40, 0))
                .status(JobStatus.CLOSED)
                .build();
    }

    @Test
    void testGetAll() {
        //arrange
        when(jobEntityRepository.getAll())
                .thenReturn(Arrays.asList(job1open, job2open, job3open, job1closed, job2closed));
        //act
        List<Job> jobs = sut.getAll();
        //assert
        assertNotNull(jobs);
        assertEquals(5, jobs.size());
        verify(jobEntityRepository, times(1)).getAll();
    }

    @Test
    void testGetAllByJobStatusStrategy() {
        //arrange
        when(jobEntityRepository.getAllByStatus(JobStatus.OPEN))
                .thenReturn(List.of(job1open, job2open, job3open));
        JobStatusStrategy strategy = JobStatusStrategy.builder().status(JobStatus.OPEN).build();
        //act
        List<Job> jobs = sut.getAllByStrategy(strategy);
        //assert
        assertNotNull(jobs);
        assertEquals(3, jobs.size());
        assertTrue(jobs.stream().allMatch(job -> job.getStatus().equals(JobStatus.OPEN)));
        verify(jobEntityRepository, times(1))
                .getAllByStatus(JobStatus.OPEN);
    }

    @Test
    void testGetAllBySortedStrategy() {
        //arrange
        final String sortField = "publishDate";
        when(jobEntityRepository.getAllSortedBy(sortField))
                .thenReturn(List.of(job3open, job1open, job2open, job2closed, job1closed));
        SortedStrategy strategy = SortedStrategy.builder().orderByField(sortField).build();
        //act
        List<Job> jobs = sut.getAllByStrategy(strategy);
        //assert
        assertNotNull(jobs);
        assertEquals(5, jobs.size());
        verify(jobEntityRepository, times(1))
                .getAllSortedBy(sortField);
    }

    @Test
    void testGetAllByStatusSortedStrategy() {
        //arrange
        final String sortField = "publishDate";
        final JobStatus status = JobStatus.OPEN;
        List<Job> mockResult = List.of(job3open, job1open, job2open);
        when(jobEntityRepository.getAllByStatusSortedBy(status, sortField))
                .thenReturn(mockResult);
        JobStatusSortedStrategy strategy = JobStatusSortedStrategy.builder()
                                            .status(status)
                                            .orderByField(sortField)
                                            .build();
        //act
        List<Job> jobs = sut.getAllByStrategy(strategy);
        //assert
        assertNotNull(jobs);
        assertEquals(3, jobs.size());
        assertTrue(jobs.stream().allMatch(job -> job.getStatus().equals(JobStatus.OPEN)));
        verify(jobEntityRepository, times(1))
                .getAllByStatusSortedBy(status, sortField);
        assertArrayEquals(mockResult.toArray(), jobs.toArray());
    }

    @Test
    void testPublishJob() {
        //arrange
        Job newJob = new Job();
        newJob.setTitle("Backend Developer");
        //act
        sut.publishJob(newJob);
        //assert
        assertEquals(JobStatus.OPEN, newJob.getStatus()); // Ensure status is set to OPEN
        verify(jobEntityRepository, times(1)).createJob(newJob);
    }
}