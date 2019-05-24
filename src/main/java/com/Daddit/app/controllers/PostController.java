package com.Daddit.app.controllers;

import com.Daddit.app.services.DadService;
import com.Daddit.app.models.Post;
import com.Daddit.app.services.PostService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private DadService dadService;

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

    @PostMapping(path = "/newPost")
    public ResponseEntity<HttpStatus> newPost(@RequestBody Post post) {
        Post newPost = postService.newPost(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId()).toUri();
        
        System.out.println(newPost + " -- " + location.getPath()); 
        return ResponseEntity.created(location).build();
    }
//    @PostMapping("/newPost")
//    public ResponseEntity<HttpStatus> newPost(@RequestBody Post post) {
//        Post result = postService.newPost(post);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
//        return ResponseEntity.created(location).build();
//    }
//    
//    @PostMapping("/newPost")
//    Post newPost(@RequestBody Post post) {
//        return postService.newPost(post);
//    }
    
}
