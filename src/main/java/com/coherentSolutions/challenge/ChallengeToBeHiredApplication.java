package com.coherentSolutions.challenge;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Hotel reservations API", version = "1.0", description = "Simple Backend application for hotel to work with reservations."))
@SpringBootApplication
public class ChallengeToBeHiredApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeToBeHiredApplication.class, args);
	}

}