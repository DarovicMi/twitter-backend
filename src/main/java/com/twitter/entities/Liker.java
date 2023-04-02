package com.twitter.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Liker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tweet likedTweet;

    @ManyToOne
    private Comment likedComment;

    @ManyToOne
    private User user;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tweet getLikedTweet() {
        return likedTweet;
    }

    public void setLikedTweet(Tweet likedTweet) {
        this.likedTweet = likedTweet;
    }

    public Comment getLikedComment() {
        return likedComment;
    }

    public void setLikedComment(Comment likedComment) {
        this.likedComment = likedComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
