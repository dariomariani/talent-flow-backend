package com.dariom.domain.strategy.impl;

import com.dariom.domain.model.Job;
import com.dariom.domain.model.JobStatus;
import com.dariom.domain.service.JobEntityRepository;
import com.dariom.domain.strategy.JobRetrieveStrategy;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JobStatusStrategy implements JobRetrieveStrategy {

    private JobStatus status;

    @Override
    public List<Job> retrieve(JobEntityRepository repository) {
        return repository.getAllByStatus(status);
    }
}
