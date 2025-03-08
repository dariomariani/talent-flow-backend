package com.dariom.domain.strategy.impl;

import com.dariom.domain.model.Job;
import com.dariom.domain.model.JobStatus;
import com.dariom.domain.service.JobEntityRepository;
import com.dariom.domain.strategy.JobRetrieveStrategy;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JobStatusSortedStrategy implements JobRetrieveStrategy {

    private JobStatus status;
    private String orderByField;

    @Override
    public List<Job> retrieve(JobEntityRepository repository) {
        return repository.getAllByStatusSortedBy(status, orderByField);
    }
}
