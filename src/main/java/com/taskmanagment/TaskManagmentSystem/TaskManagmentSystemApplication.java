package com.taskmanagment.TaskManagmentSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TaskManagmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagmentSystemApplication.class, args);
	}

}
