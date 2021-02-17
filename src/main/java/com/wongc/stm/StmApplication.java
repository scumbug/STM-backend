package com.wongc.stm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class StmApplication {

	public static void main(String[] args) {
		SpringApplication.run(StmApplication.class, args);
	}

}
