package com.Daddit.app.services;

import com.Daddit.app.models.Post;
import com.Daddit.app.repositories.PostRepository;
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

    public List<Post> findAllPostInCategory(String Category) {
        return null;
    }

    public Post newPost(Post post) {
        postRepo.save(post);
        return post;
    }
}
