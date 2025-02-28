package com.dariom.service;

import com.dariom.configuration.mapper.JobMapper;
import com.dariom.domain.model.Job;
import com.dariom.domain.service.JobDomainService;
import com.dariom.persistence.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobEntityService implements JobDomainService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobMapper jobMapper;

    @Override
    public List<Job> findOpenJobsOrderedByPublishDate() {
        return jobMapper.toJobs(jobRepository.findOpenJobsOrderedByPublishDate());
    }
}
