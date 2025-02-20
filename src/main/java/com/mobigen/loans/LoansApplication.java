package com.mobigen.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@EnableDiscoveryClient
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Loans microservice REST API Documentation",
		description = "MSA - Loans microservice REST API Documentation",
		version = "v1",
		contact = @Contact(
			name = "group1-team2",
			email = "group1-team2@email.com",
			url = "https://www.group1-team2.com"
		),
		license = @License(
			name = "Apache 2.0",
			url = "https://www.group1-team2.com"
		)
	),
	externalDocs = @ExternalDocumentation(
		description =  "MSA - Loans microservice REST API Documentation",
		url = "https://ip:port/swagger-ui.html"
	)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
