package com.dariom.configuration.mapper;

import com.dariom.domain.model.Job;
import com.dariom.dto.JobDto;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {ApplicationDtoMapper.class})
public interface JobDtoMapper {

    JobDto toJobDto(Job job);

    List<JobDto> toJobsDto(List<Job> jobs);
}
