package com.antonchar.config;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import org.hibernate.dialect.PostgreSQL94Dialect;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "db", locations = "classpath:database.yml", ignoreUnknownFields = false)
public class DatabaseConfig {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName(driverClassName);
        driver.setUrl(url);
        driver.setUsername(username);
        driver.setPassword(password);
        return driver;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.antonchar.entities");

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.POSTGRESQL);
        factoryBean.setJpaVendorAdapter(adapter);

        Map<String, Object> jpaPropertyMap = new HashMap<>();
        jpaPropertyMap.put("hibernate.dialect", PostgreSQL94Dialect.class.getName());
        jpaPropertyMap.put("hibernate.show_sql", "false");
        factoryBean.setJpaPropertyMap(jpaPropertyMap);

        return factoryBean;
    }
}
