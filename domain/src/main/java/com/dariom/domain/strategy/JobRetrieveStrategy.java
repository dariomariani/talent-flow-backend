package com.dariom.domain.strategy;

import com.dariom.domain.model.Job;
import com.dariom.domain.service.JobEntityRepository;

import java.util.List;

public interface JobRetrieveStrategy {
    List<Job> retrieve(JobEntityRepository repository);
}
