package com.askme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AskMeV2Application {

	public static void main(String[] args) {
		SpringApplication.run(AskMeV2Application.class, args);
	}
}
