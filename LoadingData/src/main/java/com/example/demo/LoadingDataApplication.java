package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableRabbit
@EnableCaching
@EnableJpaRepositories
public class LoadingDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoadingDataApplication.class, args);
	}
}