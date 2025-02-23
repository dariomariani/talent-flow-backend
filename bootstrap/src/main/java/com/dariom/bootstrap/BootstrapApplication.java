package com.dariom.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.dariom.configuration")
@ComponentScan("com.dariom.configuration.mapper")
@ComponentScan("com.dariom.controller")
@ComponentScan("com.dariom.filters")
@ComponentScan("com.dariom.service")
@EntityScan(basePackages = "com.dariom.persistence.entities")
@SpringBootApplication
public class BootstrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootstrapApplication.class, args);
	}

}
