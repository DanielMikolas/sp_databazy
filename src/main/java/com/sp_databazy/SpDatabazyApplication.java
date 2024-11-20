package com.sp_databazy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.sp_databazy.Repository")
@SpringBootApplication
@Configuration
public class SpDatabazyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpDatabazyApplication.class, args);
	}


}
