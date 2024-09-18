package com.adt.azfunction.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppConfig {
	
	@Bean
	public JdbcTemplate jdbcTemplate(DBConfig dbConfig) {
		return new JdbcTemplate(PostgreSQLConnection.getInstance(dbConfig).getDataSource());
	}
}
