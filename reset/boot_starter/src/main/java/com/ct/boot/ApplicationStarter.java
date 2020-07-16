package com.ct.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * The type Application starter.
 *
 * @author chen.cheng
 */
@SpringBootApplication
@PropertySources(@PropertySource(value = {
        "classpath:jdbc.properties",
        "classpath:global.properties"
}))
@ComponentScan("com.ct.*")
public class ApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }
}