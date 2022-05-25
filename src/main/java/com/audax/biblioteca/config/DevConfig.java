package com.audax.biblioteca.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.audax.biblioteca.domain.service.DBService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	private DBService dbService;
	
	
	public DevConfig(DBService dbService) {
		super();
		this.dbService = dbService;
	}

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;


	@Bean
	public boolean instantiateDatabase() {
		if(!"create".equals(strategy)) {
			return false;
		}
		dbService.instantiateTestDatabase();
		return true;
	}

}
