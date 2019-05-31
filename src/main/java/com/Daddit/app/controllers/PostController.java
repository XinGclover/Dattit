package com.Daddit.app.controllers;

import com.Daddit.app.models.Category;
import com.Daddit.app.models.Dad;
import com.Daddit.app.services.DadService;
import com.Daddit.app.models.Post;
import com.Daddit.app.models.Vote;
import com.Daddit.app.repositories.CategoryRepository;
import com.Daddit.app.services.CategoryService;
import com.Daddit.app.services.PostService;
import com.google.gson.Gson;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    private PostService postService;
    private DadService dadService;
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    @Autowired
    public PostController(PostService postService, DadService dadService, CategoryService categoryService, CategoryRepository categoryRepository) {
        this.postService = postService;
        this.dadService = dadService;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/getAll")
    public List<Post> getAllPosts() {
        return postService.findAllPosts();
    }

    @GetMapping("/{dadId}")
    public List<Post> getPostsFromDad(@PathVariable Long dadId) {
        return postService.findPostsFromDad(dadId);
    }

    @GetMapping("/postid/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.findPostById(id).get();
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
//        Category category = new Category(body.get("category"));
        String cateString = body.get("category");
        List<String> categoriesStrings = Arrays.asList(cateString.split(","));
        Long id = Long.parseLong(body.get("id"));
//        List<Category> categories = new ArrayList<>();
//        categories.add(category);
        // if category does not exist it will be saved in database.
//        categoryService.addCategory(category);

//        Dad dad = new Dad("teddy", "bundy");
        Dad dad = dadService.findDadById(id).get();
//        Post newPost = new Post(content, headline, categories, dad);

//        Post post = postService.newPost(new Post(content, headline, categories, dad));
//        Post post= postService.newPost(new Post(content, headline, dad));
        Post post = new Post(content, headline, dad);
        postService.newPost(post);
        List<Post> posts = new ArrayList<>();
//        posts.add(post);

        List<Category> categories = categoriesStrings.stream().map(n -> new Category(n)).collect(Collectors.toList());
        List<Category> realcategories = new ArrayList<>();

          for (Category c : categories) {           
            if (!categoryRepository.findByname(c.getName()).isPresent()) {
                posts.add(post);
                c.setPosts(posts);
                categoryService.addCategory(c);
            } else {
                Category oldCategory = categoryRepository.findByname(c.getName()).get();
                posts = oldCategory.getPosts();
                posts.add(post);
                c=oldCategory;
                c.setPosts(posts);
                categoryService.addCategory(c);
            }
            realcategories.add(c);
        }
        post.setCategories(realcategories);


        URI location = ServletUriComponentsBuilder.fromPath("http://localhost:8080").build().toUri();

//        return new RedirectView("http://localhost:8080");
        return post;
    }

    @PostMapping("/deletePost")
    public Post deletePost(@RequestBody Map<String, String> body) {
        postService.deletePostById(Long.parseLong(body.get("id")));

        return new Post();
    }

    @PostMapping("/vote")
    public Post updatePost(@RequestBody Map<String, Long> data) {

        Post post = postService.findPostById(data.get("postId")).get();

        Dad dad = dadService.findDadById(data.get("userId")).get();

        Vote vote = new Vote(data.get("voteValue").intValue(), dad, post);

        post.getVotes().add(vote);
        return postService.updatePost(post);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Post>> getSearchPosts(@RequestParam String str) {
        List<Post> searchList = postService.findPostsbyString(str);
        if (searchList.isEmpty()) {
            return ResponseEntity.badRequest().body(searchList);
        } else {
            return ResponseEntity.ok(searchList);
        }
    }

    @GetMapping("/getAll/{categoryId}")
    public List<Post> getPostsFromCategory(@PathVariable String categoryId) {
        return postService.findAllPostInCategory(new Long(categoryId));
    }
}
