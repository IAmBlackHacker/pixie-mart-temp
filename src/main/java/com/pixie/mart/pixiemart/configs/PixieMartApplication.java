package com.pixie.mart.pixiemart.configs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = { "com.pixie.mart.pixiemart.*" })
@EnableReactiveMongoRepositories("com.pixie.mart.pixiemart.repositories.*")
public class PixieMartApplication {

    public static void main(String[] args) {
        SpringApplication.run(PixieMartApplication.class, args);
    }

}
