package com.twitter.notification;

import com.twitter.entities.Tweet;

public interface Observer {
    void update(Tweet tweet);
}
