package com.example.stock.vstock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VstockApplication {

	public static void main(String[] args) {
		SpringApplication.run(VstockApplication.class, args);
	}

}
