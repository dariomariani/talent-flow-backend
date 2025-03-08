package com.dariom.domain.service;

import com.dariom.domain.model.Job;
import com.dariom.domain.strategy.JobRetrieveStrategy;

import java.util.List;

public interface JobDomainService {

    List<Job> getAll();

    List<Job> getAllByStrategy(JobRetrieveStrategy strategy);

    void publishJob(Job job);

}
