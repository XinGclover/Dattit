package com.Daddit.app.services;

import com.Daddit.app.models.Category;
import com.Daddit.app.repositories.PostRepository;
import com.Daddit.app.models.Post;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("postService")
public class PostService {

    @Autowired
    private PostRepository postRepo;
    @Autowired
    private CategoryService catservice;

    public List<Post> findAllPosts() {
        return postRepo.findAll();
    }

    public Optional<Post> findPostById(Long id) {
        return postRepo.findById(id);
    }

    public List<Post> findPostsFromDad(Long dadId) {
        return postRepo.findAll().stream().filter(p -> p.getDad().getId() == dadId).collect(Collectors.toList());
    }

    public List<Post> findtop10Posts() {

        List<Post> sortedPosts = postRepo.findAll().stream()
                .sorted((o1, o2) -> o2.getVotes().stream()
                .reduce(0, (partialsum, vote) -> partialsum + vote.getVote(), Integer::sum)
                .compareTo(o1.getVotes().stream()
                        .reduce(0, (partialsum, vote) -> partialsum + vote.getVote(), Integer::sum)))
                .limit(10).collect(Collectors.toList());

        return sortedPosts;
    }

    public List<Post> findPostsSortedByDate() {
        return postRepo.findByOrderByCreatedDesc();
    }

    public List<Post> findAllPostInCategory(Long categoryId) {
        Category c = catservice.findCategoryById(categoryId).get();
        return postRepo.findAll().stream().filter(p -> p.getCategories().contains(c)).collect(Collectors.toList());
//        return c.getPosts();
    }

    public List<Post> findPostsbyString(String str) {
        List<Post> postsbyTitle = findAllPosts().stream().filter(e -> e.getHeadline().
                toLowerCase().contains(str.toLowerCase())).collect(Collectors.toList());
        List<Post> postsbyContent = findAllPosts().stream().filter(e -> e.getContent().
                toLowerCase().contains(str.toLowerCase())).collect(Collectors.toList());
        List<Post> postsbyDadName = findAllPosts().stream().filter(e -> e.getDad().getUsername().
                toLowerCase().contains(str.toLowerCase())).collect(Collectors.toList());
        List<Post> resultList = Stream.of(postsbyTitle, postsbyContent, postsbyDadName).flatMap(x -> x.stream()).
                collect(Collectors.toList());
        return resultList;
    }

    public void newPost(Post post) {
        postRepo.save(post);
    }

}
