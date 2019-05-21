package com.Daddit.app;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/getAll")
    public List<Post> getAllPosts() {
        return postService.findAllPosts();
    }
    
    @GetMapping("/{dadId}")
    public List<Post> getPostsFromDad(@PathVariable Long dadId) {
        return postService.findPostsFromDad(dadId);
    }
    
    @GetMapping("/getTop10")
    public List<Post> getTop10Posts() {
        return postService.findtop10Posts();
    }
    
    @GetMapping("/getAllSortedByDate")
    public List<Post> getAllSortedByDate() {
        return postService.findPostsSortedByDate();
    }

}
