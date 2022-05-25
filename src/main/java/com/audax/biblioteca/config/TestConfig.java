package com.audax.biblioteca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.audax.biblioteca.domain.service.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	private DBService dbService;
	
	
	public TestConfig(DBService dbService) {
		super();
		this.dbService = dbService;
	}



	@Bean
	public boolean instantiateDatabase() {
		dbService.instantiateTestDatabase();
		return true;
	}

}
