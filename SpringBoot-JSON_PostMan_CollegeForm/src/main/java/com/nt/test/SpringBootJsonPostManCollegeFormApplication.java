package com.nt.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringBootJsonPostManCollegeFormApplication {

	public static void main(String[] args) {
		System.out.println();
		SpringApplication.run(SpringBootJsonPostManCollegeFormApplication.class, args);
	}

}
