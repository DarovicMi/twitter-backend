package com.twitter.controllers;

import com.twitter.exceptions.UserNotFoundException;
import com.twitter.model.LikerModel;
import com.twitter.entities.Liker;
import com.twitter.exceptions.CommentNotFoundException;
import com.twitter.exceptions.TweetNotFoundException;
import com.twitter.services.LikerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LikerController {

    @Autowired
    private LikerService likeService;

@PostMapping("/like/tweet/{tweetId}")
public ResponseEntity<?> likeOrUnlikeTweet(@PathVariable Long tweetId) {
    try {
        Liker like = likeService.likeTweet(tweetId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return like != null ? new ResponseEntity<>(like, headers, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.OK);
    } catch (TweetNotFoundException | UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}


@PostMapping("/like/comment/{commentId}")
public ResponseEntity<?> likeOrUnlikeComment(@PathVariable Long commentId, @RequestBody Liker liker) {
    try {
        Liker like = likeService.likeComment(commentId, liker);
        return new ResponseEntity<>(like, HttpStatus.CREATED);
    } catch (CommentNotFoundException | UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}


}
