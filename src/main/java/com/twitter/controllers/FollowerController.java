package com.twitter.controllers;


import com.twitter.entities.Follower;
import com.twitter.exceptions.UserNotFoundException;
import com.twitter.services.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @PostMapping("/follow/{userId}")
    public boolean toggleFollow(@PathVariable Long userId) throws UserNotFoundException {
        return followerService.toggleFollowUser(userId);
    }

    @GetMapping("/isFollowing/{profileUserId}")
    public ResponseEntity<Boolean> isUserFollowing(@PathVariable Long profileUserId) {
        boolean isFollowing = followerService.isUserFollowing(profileUserId);
        return ResponseEntity.ok(isFollowing);
    }

    @GetMapping("/followers/count/{userId}")
    public ResponseEntity<Long> getNumberOfFollowers(@PathVariable Long userId) {
        long count = followerService.getNumberOfFollowers(userId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/followings/count/{userId}")
    public ResponseEntity<Long> getNumberOfFollowings(@PathVariable Long userId) {
        long count = followerService.getNumberOfFollowings(userId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
