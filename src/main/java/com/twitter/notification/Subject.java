package com.twitter.notification;

import com.twitter.entities.Tweet;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Tweet tweet);
}
