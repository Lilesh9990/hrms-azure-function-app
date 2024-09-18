package com.adt.azfunction.repository;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class PostgreSQLConnection {

	private static PostgreSQLConnection instance;

	private DriverManagerDataSource dataSource;

	private PostgreSQLConnection(DBConfig dbConfig) {
		dataSource = new DriverManagerDataSource();
		dataSource.setUrl(dbConfig.getUrl());
		dataSource.setUsername(dbConfig.getUsername());
		dataSource.setPassword(dbConfig.getPassword());
		dataSource.setDriverClassName(dbConfig.getDriverClassName());
	}

	public static synchronized PostgreSQLConnection getInstance(DBConfig dbConfig) {
		if (instance == null) {
			instance = new PostgreSQLConnection(dbConfig);
		}
		return instance;
	}

	public DriverManagerDataSource getDataSource() {
		return dataSource;
	}
}