package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import java.util.Date;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DemoApplication {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner intro() {
        return (args) -> {
            log.info(String.format("Demo: %s", env.getProperty("initial.version")));
            log.info(String.format("Env: %s ", env.getProperty("env.key")));
            log.info(String.format("Date: %s", new Date()));
        };
    }

}
