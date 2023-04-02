package com.twitter.controllers;

import com.twitter.entities.Comment;
import com.twitter.entities.User;
import com.twitter.exceptions.CommentNotFoundException;
import com.twitter.exceptions.TweetNotFoundException;
import com.twitter.exceptions.UserNotFoundException;
import com.twitter.services.CommentService;
import com.twitter.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserAuthenticationService userAuthenticationService;


    @PostMapping("/tweet/{tweetId}/comment")
    public Comment createComment(@PathVariable Long tweetId, @RequestBody Comment comment) throws TweetNotFoundException {
        commentService.createTweetComment(tweetId, comment);
        return comment;
    }

    @GetMapping("/tweet/{tweetId}/comments")
    public List<Comment> getTweetComments(@PathVariable Long tweetId) throws TweetNotFoundException {
        return commentService.getTweetComments(tweetId);
    }

    @DeleteMapping("/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId) throws Exception {
        commentService.deleteComment(commentId);
    }

    @PutMapping("/comment/{commentId}")
    public void updateComment(@PathVariable("commentId") Long commentId, @RequestBody Comment comment) throws Exception {
        commentService.updateComment(commentId, comment);
    }
    @PostMapping("/comment/{commentId}/replies")
    public Comment createReply(@PathVariable Long commentId, @RequestBody String text) throws CommentNotFoundException {
        User user = userAuthenticationService.getLoggedInUser();
        return commentService.createReply(commentId, user, text);
    }

    @GetMapping("/comment/{commentId}/replies")
    public ResponseEntity<List<Comment>> getReplies(@PathVariable("commentId") Long commentId,
                                                    @RequestParam(value = "isReply", defaultValue = "true") boolean isReply) throws CommentNotFoundException {
        List<Comment> replies = commentService.getReplies(commentId, isReply);
        return ResponseEntity.ok(replies);
    }

@GetMapping("/comment/count/{tweetId}")
    public ResponseEntity<Long> getCommentCountByTweetId(@PathVariable Long tweetId) {
        Long commentCount = commentService.countCommentsByTweetId(tweetId);
        return new ResponseEntity<>(commentCount, HttpStatus.OK);
    }



}
