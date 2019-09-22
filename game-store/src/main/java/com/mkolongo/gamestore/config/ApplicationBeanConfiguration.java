package com.mkolongo.gamestore.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Scanner;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
}
