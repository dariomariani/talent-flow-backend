package com.dariom.domain.service.impl;

import com.dariom.domain.model.Job;
import com.dariom.domain.model.JobRetrieveStrategy;
import com.dariom.domain.model.JobStatus;
import com.dariom.domain.service.JobDomainService;
import com.dariom.domain.service.JobEntityRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class JobDomainServiceImpl implements JobDomainService {

    private final JobEntityRepository jobEntityRepository;

    public JobDomainServiceImpl(JobEntityRepository jobEntityRepository) {
        this.jobEntityRepository = jobEntityRepository;
    }

    @Override
    public List<Job> getAll() {
        return jobEntityRepository.getAll();
    }

    @Override
    public List<Job> getAllByStrategy(JobRetrieveStrategy strategy) {
        Stream<Job> jobStream = jobEntityRepository.getAll().stream()
                .filter(job -> strategy.isOpen() == job.isOpen());

        Comparator<Job> comparator = getJobComparator(strategy.getOrderByField());
        if (comparator != null) {
            jobStream = jobStream.sorted(comparator);
        }

        return jobStream.toList();
    }


    @Override
    public void createJob(Job job) {
        job.setStatus(JobStatus.OPEN);
        jobEntityRepository.createJob(job);
    }

    private Comparator<Job> getJobComparator(String fieldName) {
        if (fieldName == null) return null;
        return switch (fieldName) {
            case "publishDate" -> Comparator.comparing(Job::getPublishDate);
            case "description" -> Comparator.comparing(Job::getDescription);
            default -> null;
        };
    }
}
