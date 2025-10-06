package com.example.beautyconnectapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class BeautyconnectApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeautyconnectApiApplication.class, args);
	}

}
