package com.antonchar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.antonchar")
@SpringBootApplication
public class SimpleWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleWebServiceApplication.class, args);
	}
}
