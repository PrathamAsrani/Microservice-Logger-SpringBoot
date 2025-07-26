package com.logger.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LoggerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggerServiceApplication.class, args);
	}

}
