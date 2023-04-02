package com.twitter.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonIgnore
    private Tweet tweet;

    @ManyToOne
    @JsonIgnore
    private Comment parentComment;
@OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<Comment> replies;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean edited = false;

    @Column
    private boolean isReply;

    public boolean isReply() {
        return isReply;
    }

    public void setReply(boolean reply) {
        isReply = reply;
    }



    public Comment(Long id, String text, User user, Tweet tweet, Comment parentComment , LocalDateTime createdAt, LocalDateTime updatedAt, boolean edited) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.tweet = tweet;
        this.parentComment = parentComment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.edited = edited;
    }
    public Comment() {

    }

    @OneToMany(mappedBy = "likedTweet", cascade = CascadeType.REMOVE)
    private List<Liker> likes;

    public List<Liker> getLikes() {
        return likes;
    }

    public void setLikes(List<Liker> likes) {
        this.likes = likes;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public User getUser() {
        return user;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTweet(Tweet tweet) {
        tweet.addComment(this);
        this.tweet = tweet;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public List<Comment> getReplies() {
        return replies;
    }
    public void addReply(Comment reply) {
        this.replies.add(reply);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}