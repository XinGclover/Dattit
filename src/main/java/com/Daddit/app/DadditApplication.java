package com.Daddit.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DadditApplication {

    public static void main(String[] args) {
        SpringApplication.run(DadditApplication.class, args);
    }
    
    @Bean
    CommandLineRunner init(DadRepository dadRepo) {
        return args -> {
            dadRepo.save(new Dad("daddy1", "daddy", false));
            dadRepo.save(new Dad("daddy2", "daddy", false));
            dadRepo.save(new Dad("daddy3", "daddy", false));
            dadRepo.save(new Dad("BigDaddy", "chef", true));
        };
    }
    

}
