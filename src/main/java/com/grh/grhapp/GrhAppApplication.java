package com.grh.grhapp;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class GrhAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrhAppApplication.class, args);
	}
	
	// @Bean
	ApplicationRunner applicationRunner(Environment environment) {
		return args -> {
			System.out.println(environment.getProperty("msg"));
		};		
	}

}
