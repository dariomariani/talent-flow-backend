package com.dariom.domain.service;

import com.dariom.domain.model.Job;
import com.dariom.domain.model.JobStatus;

import java.util.List;

public interface JobEntityRepository {

    List<Job> getAll();

    List<Job> getAllSortedBy(String fieldName);

    List<Job> getAllByStatus(JobStatus status);

    List<Job> getAllByStatusSortedBy(JobStatus status, String fieldName);

    void createJob(Job job);
}
