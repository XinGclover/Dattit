package com.Daddit.app;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class DadditApplication {

    public static void main(String[] args) {
        SpringApplication.run(DadditApplication.class, args);
    }
    
    @Bean
    CommandLineRunner init(DadRepository dadRepo, PostRepository postRepo, VoteRepository voteRepo, CategoryRepository categoryRepo) {
        return args -> {
            Dad dad1 = new Dad("daddy1", "daddy", false);
            Dad dad2 = new Dad("daddy2", "daddy", false);
            Dad dad3 = new Dad("daddy3", "daddy", false);
            Dad dad4 = new Dad("BigDaddy", "chef", true);
            
            dadRepo.save(dad1);
            dadRepo.save(dad2);
            dadRepo.save(dad3);
            dadRepo.save(dad4);
            
            postRepo.save(new Post("This is sucha goodish post", dad1));
            postRepo.save(new Post("Best post ever", dad1));
            postRepo.save(new Post("Jokes jokes jokes", dad2));
            postRepo.save(new Post("Lolzor", dad3));            
            
            
        };
    }
}
