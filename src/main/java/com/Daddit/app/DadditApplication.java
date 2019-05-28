package com.Daddit.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
            

            Post post1 = new Post("A dad walks into a bar", "My first joke",  dad1);
            Post post2 = new Post("A dad walks out of a shop", "My second joke",  dad2);
            postRepo.save(post1);
            postRepo.save(post2);

            List<Post> posts = new ArrayList<>();
            posts.add(post1);
            posts.add(post2);

            
            List<Category> categories = new ArrayList<>();
            List<Category> categories2 = new ArrayList<>();

            Category category1 = new Category("r-rated");
            Category category2 = new Category("mild");
            Category category3 = new Category("mom joke");
            Category category4 = new Category("holiday");
            
            categories.add(category1);
            categories.add(category2);
            categories.add(category3);
            categories.add(category4);
            category1.setPosts(posts);
            category2.setPosts(posts);
            category3.setPosts(posts);
            category4.setPosts(posts);
            
            categoryRepo.save(category1);
            categoryRepo.save(category2);
            categoryRepo.save(category3);
            categoryRepo.save(category4);
            

            List<Vote> votesList = new ArrayList<>();

            Vote vote1 = new Vote(1, dad3, post1);
            Vote vote2 = new Vote(1, dad2, post1);
            Vote vote3 = new Vote(1, dad4, post1);
            Vote vote4 = new Vote(1, dad1, post1);

            votesList.add(vote1);
            votesList.add(vote2);
            votesList.add(vote3);
            votesList.add(vote4);
            
            List<Vote> votesList2 = new ArrayList<>();

            Vote vote5 = new Vote(1, dad3, post2);
            Vote vote6 = new Vote(1, dad2, post2);
           

            votesList2.add(vote5);
            votesList2.add(vote6);
//            
//        
            post1.setCategories(categories);
            post1.setVotes(votesList);
            //postRepo.save(post1);
//            
            post2.setCategories(categories);
            post2.setVotes(votesList2);
            
            

            // CREATE MULITPLE POSTS WITH VOTES



            /*
            Post post1 = new Post("A dad walks into a bar", dad1);
            Post post2 = new Post("Your mom is so fat when she jumped for joy she got stuck", dad1);
            
            List<Category> categoriesForFirstPost = new ArrayList<>();
            List<Category> categoriesForSecondPost = new ArrayList<>();
            
            Category category1 = new Category("r-rated");
            Category category2 = new Category("mild");
            Category category3 = new Category("mom joke");
            
            categoriesForFirstPost.add(category1);
            categoriesForFirstPost.add(category2);
            categoriesForFirstPost.add(category3);
            
            categoriesForSecondPost.add(category3);
            
            List<Vote> votesForFirstPost = new ArrayList<>();
            List<Vote> votesForSecondPost = new ArrayList<>();
            
            Vote vote1 = new Vote(1, dad3, post1);
            Vote vote2 = new Vote(1, dad2, post1);
            votesForFirstPost.add(vote1);
            votesForFirstPost.add(vote2);
            
            Vote vote3 = new Vote(1, dad2, post2);
            Vote vote4 = new Vote(1, dad3, post2);
            Vote vote5 = new Vote(1, dad4, post2);
            votesForSecondPost.add(vote3);
            votesForSecondPost.add(vote4);
            votesForSecondPost.add(vote5);
            
            post1.setCategories(categoriesForFirstPost);
            post1.setVotes(votesForFirstPost);
            
            post2.setCategories(categoriesForSecondPost);
            post2.setVotes(votesForSecondPost);
            
            postRepo.save(post1);
            postRepo.save(post2);
  
            */
        };
    }
}
