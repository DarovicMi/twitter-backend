package com.twitter.notification;

import com.twitter.entities.Tweet;
import com.twitter.entities.User;

public class UserObserver implements Observer {

    private final User user;

    public UserObserver(User user) {
        this.user = user;
    }

    @Override
    public void update(Tweet tweet) {
        System.out.println("User " + user.getUsername() + " received a notification about a new tweet: " + tweet.getMessage());
    }

}
