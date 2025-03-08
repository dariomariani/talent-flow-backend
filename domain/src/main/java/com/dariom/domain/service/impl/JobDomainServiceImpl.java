package com.dariom.domain.service.impl;

import com.dariom.domain.model.Job;
import com.dariom.domain.model.JobStatus;
import com.dariom.domain.service.JobDomainService;
import com.dariom.domain.service.JobEntityRepository;
import com.dariom.domain.strategy.JobRetrieveStrategy;

import java.util.List;

public class JobDomainServiceImpl implements JobDomainService {

    private final JobEntityRepository jobEntityRepository;

    public JobDomainServiceImpl(JobEntityRepository jobEntityRepository) {
        this.jobEntityRepository = jobEntityRepository;
    }

    @Override
    public List<Job> getAll() {
        return jobEntityRepository.getAll();
    }

    @Override
    public List<Job> getAllByStrategy(JobRetrieveStrategy strategy) {
        return strategy.retrieve(jobEntityRepository);
    }

    @Override
    public void publishJob(Job job) {
        job.setStatus(JobStatus.OPEN);
        jobEntityRepository.createJob(job);
    }

}
