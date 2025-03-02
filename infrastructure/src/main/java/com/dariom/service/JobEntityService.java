package com.dariom.service;

import com.dariom.configuration.mapper.JobMapper;
import com.dariom.domain.model.Job;
import com.dariom.domain.service.JobEntityRepository;
import com.dariom.persistence.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createJob(Job job) {
        jobRepository.save(jobMapper.toJobEntity(job));
    }

    /*@Override
    public List<Job> getOpenJobsOrderedByPublishDate() {
        return jobMapper.toJobs(jobRepository.findAll((Sort.by(Sort.Direction.ASC, "publishDate"))));
    }*/


}
