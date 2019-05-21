package com.Daddit.app;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
//    @ManyToMany
//    private List<Category> categories;
    @OneToMany
    private List<Vote> votes;
    private LocalDate created;

    public Post() {
    }

    public Post(String content, /*List<Category> categories,*/ List<Vote> votes) {
        this.content = content;
//        this.categories = categories;
        this.votes = votes;
        this.created = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public List<Category> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(List<Category> categories) {
//        this.categories = categories;
//    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
    
    
    
    
}
