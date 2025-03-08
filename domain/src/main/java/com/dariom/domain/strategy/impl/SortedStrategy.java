package com.dariom.domain.strategy.impl;

import com.dariom.domain.model.Job;
import com.dariom.domain.service.JobEntityRepository;
import com.dariom.domain.strategy.JobRetrieveStrategy;
import lombok.*;

import java.util.Comparator;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SortedStrategy implements JobRetrieveStrategy {

    private String orderByField;

    @Override
    public List<Job> retrieve(JobEntityRepository repository) {
        return repository.getAllSortedBy(orderByField);
    }
}
