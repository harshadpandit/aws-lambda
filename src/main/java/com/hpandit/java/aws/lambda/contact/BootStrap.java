package com.hpandit.java.aws.lambda.contact;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class BootStrap {

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }

    @Bean
    public SESMailer sesMailer() {
        return new SESMailer();
    }

    @Bean
    public LambdaContactFormHandler lambdaContactFormHandler() {
        return new LambdaContactFormHandler();
    }

}
