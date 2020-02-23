package com.ct.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * The type Application starter.
 *
 * @author chen.cheng
 */
@SpringBootApplication
@ImportResource(value = {
       "classpath:spring-mvc.xml"
})
public class ApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }
}