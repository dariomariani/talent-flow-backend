package com.dariom.controller;

import com.dariom.configuration.mapper.JobDtoMapper;
import com.dariom.domain.model.JobRetrieveStrategy;
import com.dariom.domain.service.JobDomainService;
import com.dariom.dto.ApiResponse;
import com.dariom.dto.JobDto;
import com.dariom.dto.NewJobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@RequestMapping("/jobs")
@RestController
public class JobController {

    @Autowired
    private JobDomainService jobDomainService;

    @Autowired
    private JobDtoMapper jobDtoMapper;

    @GetMapping()
    public ApiResponse<List<JobDto>> getJobs(@RequestParam(name = "isOpen", required = false) Boolean isOpen,
                                             @RequestParam(name = "orderBy", required = false) String orderByField,
                                             WebRequest webRequest) {

        Map<String, String[]> queryParams = webRequest.getParameterMap();
        if (queryParams.isEmpty()) {
            return ApiResponse.success(jobDtoMapper.toJobsDto(jobDomainService.getAll()));
        }

        JobRetrieveStrategy strategy = JobRetrieveStrategy.builder().build();

        if (queryParams.containsKey("isOpen")) strategy = strategy.toBuilder()
                .open(Boolean.parseBoolean(queryParams.get("isOpen")[0]))
                .build();

        if (queryParams.containsKey("orderBy")) strategy = strategy.toBuilder()
                .orderByField(queryParams.get("orderBy")[0])
                .build();

        return ApiResponse.success(jobDtoMapper.toJobsDto(jobDomainService.getAllByStrategy(strategy)));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<Void> createJob(@RequestBody NewJobDto job) {
        jobDomainService.createJob(jobDtoMapper.toJob(job));
        return ApiResponse.success();
    }

}
