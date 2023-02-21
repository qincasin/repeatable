package com.qjx.sample;

import com.qjx.sample.spring.importaware.EnableProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicSampleApplication.class, args);
    }

}
