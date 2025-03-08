package com.dariom.controller;

import com.dariom.configuration.mapper.JobDtoMapper;
import com.dariom.domain.service.JobDomainService;
import com.dariom.domain.strategy.JobRetrieveStrategy;
import com.dariom.domain.strategy.JobRetrieveStrategyFactory;
import com.dariom.dto.ApiResponse;
import com.dariom.dto.JobDto;
import com.dariom.dto.NewJobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RequestMapping("/jobs")
@RestController
public class JobController {

    @Autowired
    private JobDomainService jobDomainService;

    @Autowired
    private JobDtoMapper jobDtoMapper;

    @GetMapping()
    public ApiResponse<List<JobDto>> getJobs(@RequestParam(name = "status", required = false) String status,
                                             @RequestParam(name = "orderBy", required = false) String orderByField,
                                             WebRequest webRequest) {

        if (webRequest.getParameterMap().isEmpty()) {
            return ApiResponse.success(jobDtoMapper.toJobsDto(jobDomainService.getAll()));
        }

        JobRetrieveStrategy jobRetrieveStrategy = JobRetrieveStrategyFactory.createStrategy(status, orderByField);

        return ApiResponse.success(jobDtoMapper.toJobsDto(jobDomainService.getAllByStrategy(jobRetrieveStrategy)));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<Void> createJob(@RequestBody NewJobDto job) {
        jobDomainService.publishJob(jobDtoMapper.toJob(job));
        return ApiResponse.success();
    }

}
