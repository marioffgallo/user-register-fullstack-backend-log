package com.marioffgallo.userregisterfullstackbackendlog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Microservice responsible for saving logs and listen ActiveMQ
 *
 * @author Mario F.F Gallo
 * @version 1.0
 */
@SpringBootApplication
public class UserRegisterFullstackBackendLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserRegisterFullstackBackendLogApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
