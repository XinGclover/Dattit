package com.Daddit.app;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service("postService")
public class PostService {
    
    @Autowired
    private PostRepository postRepo;
    
    List<Post> findAllPosts() {
        return postRepo.findAll();
    }
    
    Optional<Post> findPostById(Long id) {
        return postRepo.findById(id);
    }
    
    
    List<Post> findPostsFromDad(Long dadId) {
        return postRepo.findAll().stream().filter(p -> p.getDad().getId() == dadId).collect(Collectors.toList());
    }

}
