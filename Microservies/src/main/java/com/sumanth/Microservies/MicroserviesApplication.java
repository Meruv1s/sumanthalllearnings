package com.sumanth.Microservies;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MicroserviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviesApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapperr(){
		return new ModelMapper();
	}
}
