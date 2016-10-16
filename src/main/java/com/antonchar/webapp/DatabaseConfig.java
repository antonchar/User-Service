package com.antonchar.webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName("com.mysql.jdbc.Driver");
        driver.setUrl("jdbc:mysql://localhost:3306/test");
        driver.setUsername("root");
        driver.setPassword("root");
        return driver;
    }
}
