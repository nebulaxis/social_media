package com.social.media.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Integer id;


    private String content;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<User> Liked = new ArrayList<>();

    private LocalDateTime createdAt;


    public Comment(){

    }

    

    public Comment(Integer id, String content, User user, List<User> liked, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.user = user;
        Liked = liked;
        this.createdAt = createdAt;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getLiked() {
        return Liked;
    }

    public void setLiked(List<User> liked) {
        Liked = liked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }



    
    
}
