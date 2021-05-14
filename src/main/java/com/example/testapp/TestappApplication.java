package com.example.testapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TestappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestappApplication.class, args);
	}

}
