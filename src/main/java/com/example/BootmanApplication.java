package com.example;

import com.example.service.repos.BaseRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
@EnableJpaRepositories(value = "com.example.service.repos",repositoryBaseClass = BaseRepositoryImpl.class)
@Slf4j
public class BootmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootmanApplication.class, args);
	}
}

