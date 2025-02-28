package com.dariom.controller;

import com.dariom.domain.service.JobDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/jobs")
@RestController
public class JobController {

    @Autowired
    private JobDomainService jobDomainService;

}
