package com.flightapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class AirlinebookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlinebookingApplication.class, args);
	}

}
