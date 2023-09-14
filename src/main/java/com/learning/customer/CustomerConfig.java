package com.learning.customer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Value("${app.checkValue:true}")
    private Boolean checkValue;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println(checkValue);
        };
    }
}
