package com.dariom.configuration;

import com.dariom.domain.service.JobDomainService;
import com.dariom.domain.service.JobEntityRepository;
import com.dariom.domain.service.impl.JobDomainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Autowired
    private JobEntityRepository jobEntityRepository;

    @Bean
    public JobDomainService jobDomainService(JobEntityRepository jobEntityRepository) {
        return new JobDomainServiceImpl(jobEntityRepository);
    }
}
