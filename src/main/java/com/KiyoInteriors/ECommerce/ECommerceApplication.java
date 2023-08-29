package com.KiyoInteriors.ECommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * The main class of the E-commerce application.
 * This class contains the entry point for the application.
*/
/**
* The main method that serves as the entry point for the E-commerce application.
* It starts the Spring Boot application by running the SpringApplication.run() method.
*
* @param args The command-line arguments passed to the application.
*/
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}
