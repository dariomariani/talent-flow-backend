package com.dariom.domain.strategy.impl;

import com.dariom.domain.model.Job;
import com.dariom.domain.service.JobEntityRepository;
import com.dariom.domain.strategy.JobRetrieveStrategy;

import java.util.List;

public class DefaultRetrieveStrategy implements JobRetrieveStrategy {
    @Override
    public List<Job> retrieve(JobEntityRepository repository) {
        return repository.getAll();
    }
}
