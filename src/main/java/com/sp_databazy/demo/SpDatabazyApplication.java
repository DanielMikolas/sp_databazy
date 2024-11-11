package com.sp_databazy.demo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
public class SpDatabazyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpDatabazyApplication.class, args);
	}

}
