package com.korsuk.my_market.configs;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.yaml")
public class PropertiesWithConfig {

//    @Bean(name = "postgresDataSource")
//    public DataSource mySqlDataSource()
//    {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/postgres");
//        dataSourceBuilder.username("postgres");
//        dataSourceBuilder.password("berbedos31");
//        return dataSourceBuilder.build();
//    }
}
