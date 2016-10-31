package com.example;

import com.example.service.repos.MyRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Validator;

@EnableCaching
@EnableScheduling
@SpringBootApplication
@EnableJpaRepositories(value = "com.example.service.repos",repositoryBaseClass = MyRepositoryImpl.class)
@Slf4j
public class BootmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootmanApplication.class, args);
	}
}

