package com.github.kanttanhed.mscreditevaluater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class MscreditevaluatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscreditevaluatorApplication.class, args);
	}

}
