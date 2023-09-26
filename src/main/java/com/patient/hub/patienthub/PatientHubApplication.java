package com.patient.hub.patienthub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.patient.hub.patienthub.repository")
@EnableCaching
@EnableJpaAuditing
public class PatientHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientHubApplication.class, args);
	}

}
