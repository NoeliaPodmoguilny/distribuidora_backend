package com.distribuidora.distri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DistriApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistriApplication.class, args);
	}

}
