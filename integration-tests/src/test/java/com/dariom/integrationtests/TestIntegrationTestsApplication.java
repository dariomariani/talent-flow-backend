package com.dariom.integrationtests;

import org.springframework.boot.SpringApplication;

public class TestIntegrationTestsApplication {

	public static void main(String[] args) {
		SpringApplication.from(IntegrationTestsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
