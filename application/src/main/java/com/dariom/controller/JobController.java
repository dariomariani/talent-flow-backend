package com.dariom.controller;

import com.dariom.configuration.mapper.JobDtoMapper;
import com.dariom.domain.service.JobDomainService;
import com.dariom.dto.ApiResponse;
import com.dariom.dto.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/jobs")
@RestController
public class JobController {

    @Autowired
    private JobDomainService jobDomainService;

    @Autowired
    private JobDtoMapper jobDtoMapper;

    @GetMapping()
    public ApiResponse<List<JobDto>> getJobs() {
        return ApiResponse.success(jobDtoMapper.toJobsDto(jobDomainService.getAll()));
    }

}
