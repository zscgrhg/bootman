package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BootmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootmanApplication.class, args);
		while (true){
			log.debug("this is test log rolling");
		}
	}
}
