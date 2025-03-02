package com.dariom.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.dariom.configuration")
@ComponentScan("com.dariom.configuration.mapper")
@ComponentScan("com.dariom.controller")
@ComponentScan("com.dariom.filters")
@ComponentScan("com.dariom.service")
@EnableJpaRepositories(basePackages = "com.dariom.persistence.repositories")
@EntityScan(basePackages = "com.dariom.persistence.entities")
@SpringBootApplication
public class BootstrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootstrapApplication.class, args);
	}

}
