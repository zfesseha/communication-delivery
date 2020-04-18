package com.chronicle.communications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class CommunicationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunicationsApplication.class, args);
	}

	// TODO: Logging config
	// TODO: Global error handling
	// TODO: Figure out why collections in API models have to be wrapped with Optional
	// TODO: Test Coverage
	// TODO: Checkstyle
}
