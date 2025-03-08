package com.dariom.domain.strategy;

import com.dariom.domain.model.JobStatus;
import com.dariom.domain.strategy.impl.DefaultRetrieveStrategy;
import com.dariom.domain.strategy.impl.JobStatusSortedStrategy;
import com.dariom.domain.strategy.impl.JobStatusStrategy;
import com.dariom.domain.strategy.impl.SortedStrategy;

public class JobRetrieveStrategyFactory {

    public static JobRetrieveStrategy createStrategy(String status, String orderByField) {
        boolean hasStatus = status != null && !status.isEmpty();
        boolean hasOrderBy = orderByField != null && !orderByField.isEmpty();

        return switch ((hasStatus ? 1 : 0) + (hasOrderBy ? 2 : 0)) {
            case 1 -> new JobStatusStrategy(JobStatus.valueOf(status));
            case 2 -> new SortedStrategy(orderByField);
            case 3 -> new JobStatusSortedStrategy(JobStatus.valueOf(status), orderByField);
            default -> new DefaultRetrieveStrategy();
        };
    }
}
