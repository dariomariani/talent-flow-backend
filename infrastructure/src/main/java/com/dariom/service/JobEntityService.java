package com.dariom.service;

import com.dariom.configuration.mapper.JobMapper;
import com.dariom.domain.model.Job;
import com.dariom.domain.model.JobStatus;
import com.dariom.domain.service.JobEntityRepository;
import com.dariom.persistence.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobEntityService implements JobEntityRepository {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobMapper jobMapper;

    @Override
    public List<Job> getAll() {
        return jobMapper.toJobs(jobRepository.findAll());
    }

    @Override
    public List<Job> getAllSortedBy(String fieldName) {
        Sort sort = Sort.by(fieldName);
        return jobMapper.toJobs(jobRepository.findAll(sort));
    }

    @Override
    public List<Job> getAllByStatus(JobStatus status) {
        return jobMapper.toJobs(jobRepository.findByStatus(status.name()));
    }

    @Override
    public List<Job> getAllByStatusSortedBy(JobStatus status, String fieldName) {
        Sort sort = Sort.by(fieldName);
        return jobMapper.toJobs(jobRepository.findByStatus(status.name(), sort));
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(jobMapper.toJobEntity(job));
    }

}
