package com.kmmx.curso.microservices.Microservices01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ServletComponentScan
@SpringBootApplication(scanBasePackages = "com.kmmx.curso.microservices.Microservices01")
@EnableJpaRepositories("com.kmmx.curso.microservices.Microservices01.repository")
@EntityScan("com.kmmx.curso.microservices.Microservices01.models")
public class Microservices01Application {

	public static void main(String[] args) {
		SpringApplication.run(Microservices01Application.class, args);
	}

}
