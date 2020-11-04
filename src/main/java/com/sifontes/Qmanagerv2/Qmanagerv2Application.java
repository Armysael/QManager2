package com.sifontes.Qmanagerv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class Qmanagerv2Application {

	public static void main(String[] args) {
		SpringApplication.run(Qmanagerv2Application.class, args);
	}

}
