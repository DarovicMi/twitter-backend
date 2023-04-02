package com.twitter.notification;

import com.twitter.entities.Tweet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TweetPublisher implements Subject{

    private List<Observer> observers;

    public TweetPublisher() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
    observers.remove(observer);
    }

    @Override
    public void notifyObservers(Tweet tweet) {
        for(Observer observer : observers) {
            observer.update(tweet);
        }
    }
}
