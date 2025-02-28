package com.dariom.domain.service;

import com.dariom.domain.model.Job;

import java.util.List;

public interface JobDomainService {

    List<Job> findOpenJobsOrderedByPublishDate();
}
