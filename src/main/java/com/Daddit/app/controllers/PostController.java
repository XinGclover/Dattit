package com.Daddit.app.controllers;

import com.Daddit.app.models.Category;
import com.Daddit.app.models.Dad;
import com.Daddit.app.services.DadService;
import com.Daddit.app.models.Post;
import com.Daddit.app.repositories.CategoryRepository;
import com.Daddit.app.services.CategoryService;
import com.Daddit.app.services.PostService;
import com.google.gson.Gson;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private DadService dadService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

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

    @PostMapping("/newPost")
    public Post newPost(@RequestBody Map<String, String> body) {
        
        String headline = body.get("headline");
        String content = body.get("content");
        Category category = new Category(body.get("category"));
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        // if category does not exist it will be saved in database.
        categoryService.addCategory(category);

//        Dad dad = new Dad("teddy", "bundy");
        Dad dad = dadService.findDadById(1).get();
//        Post newPost = new Post(content, headline, categories, dad);

        Post post = postService.newPost(new Post(content, headline, categories, dad));

        URI location = ServletUriComponentsBuilder.fromPath("http://localhost:8080").build().toUri();

//        return new RedirectView("http://localhost:8080");
        return post;
    }
    
    @PostMapping("/deletePost")
    public Post deletePost(@RequestBody Map<String, String> body) {
        postService.deletePostById(Long.parseLong(body.get("id")));
        
        return new Post();
    }
}
