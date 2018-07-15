package com.n26.transaction.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Start-Up Class for the spring-boot web application
 * 
 * @author Sanjib Pramanick
 *
 */
@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class TransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionServiceApplication.class, args);
	}
}
