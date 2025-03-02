package com.dariom.configuration.mapper;

import com.dariom.domain.model.Job;
import com.dariom.persistence.entities.JobEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ApplicationMapper.class })
public interface JobMapper {

    Job toJob(JobEntity jobEntity);

    JobEntity toJobEntity(Job job);

    List<Job> toJobs(List<JobEntity> jobEntities);

}
