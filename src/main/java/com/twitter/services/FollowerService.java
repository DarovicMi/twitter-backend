package com.twitter.services;

import com.twitter.entities.Follower;
import com.twitter.entities.User;
import com.twitter.exceptions.UserNotFoundException;
import com.twitter.notification.TweetPublisher;
import com.twitter.repositories.FollowerRepository;
import com.twitter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowerRepository followerRepository;

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Autowired
private TweetPublisher tweetPublisher;

    public boolean toggleFollowUser(Long userId) throws UserNotFoundException {
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        User followee = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Error 404 - User not found"));

        if (loggedInUser != null && followee != null) {
            Follower existingFollower = followerRepository.findByFollowerAndFollowing(loggedInUser, followee);
            if (existingFollower != null) {
                followerRepository.delete(existingFollower);
                return false;
            } else {
                Follower newFollower = new Follower();
                newFollower.setFollower(loggedInUser);
                newFollower.setFollowing(followee);
                followerRepository.save(newFollower);
                return true;
            }
        }
        return false;
    }

    public boolean isUserFollowing(Long profileUserId) {
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        User profileUser = userRepository.findById(profileUserId).orElse(null);

        if (loggedInUser != null && profileUser != null) {
            Follower existingFollower = followerRepository.findByFollowerAndFollowing(loggedInUser, profileUser);
            return existingFollower != null;
        }

        return false;
    }

    public long getNumberOfFollowers(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return followerRepository.countByFollowing(user);
        }
        return 0;
    }

    public long getNumberOfFollowings(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return followerRepository.countByFollower(user);
        }
        return 0;
    }

}
