package com.example.projecttrendshopapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectTrendshopAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectTrendshopAppApplication.class, args);
	}
}
