package com.dariom.integrationtests;

import com.dariom.domain.service.JobDomainService;
import com.dariom.domain.service.JobEntityRepository;
import com.dariom.domain.service.impl.JobDomainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestBeanConfiguration {

    @Autowired
    private JobEntityRepository jobEntityRepository;

    @Bean
    public JobDomainService jobDomainService(JobEntityRepository jobEntityRepository) {
        return new JobDomainServiceImpl(jobEntityRepository);
    }
}
