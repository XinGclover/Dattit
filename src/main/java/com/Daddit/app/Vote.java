package com.Daddit.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Vote {

    @Id
    @GeneratedValue
    private Long id;
    private int vote;
    @OneToOne
    private Dad dad;
    @ManyToOne
    private Post post;

    public Vote() {
    }

    public Vote(int vote, Dad dad, Post post) {
        this.vote = vote;
        this.dad = dad;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public Dad getDad() {
        return dad;
    }

    public void setDad(Dad dad) {
        this.dad = dad;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    
    
}
