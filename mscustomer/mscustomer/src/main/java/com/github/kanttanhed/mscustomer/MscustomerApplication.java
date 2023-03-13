package com.github.kanttanhed.mscustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
public class MscustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscustomerApplication.class, args);
	}

}
