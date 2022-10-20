package com.company.stochasticmodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.company.stochasticmodel"})
public class StochasticmodelApplication {

	public static void main(String[] args) {
		SpringApplication.run(StochasticmodelApplication.class, args);
	}
}
