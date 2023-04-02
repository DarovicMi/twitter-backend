package com.twitter.notification;

import com.twitter.entities.Tweet;
import com.twitter.entities.User;

import javax.persistence.*;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @ManyToOne
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    private boolean isRead;

    public Notification(Long id, User recipient, Tweet tweet, boolean isRead) {
        this.id = id;
        this.recipient = recipient;
        this.tweet = tweet;
        this.isRead = isRead;
    }
    public  Notification() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}