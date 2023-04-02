package com.twitter.services;

import com.twitter.entities.Comment;
import com.twitter.entities.Liker;
import com.twitter.entities.Tweet;
import com.twitter.entities.User;
import com.twitter.exceptions.CommentNotFoundException;
import com.twitter.exceptions.TweetNotFoundException;
import com.twitter.exceptions.UserNotFoundException;
import com.twitter.repositories.CommentRepository;
import com.twitter.repositories.LikerRepository;
import com.twitter.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LikerService {

    @Autowired
    private LikerRepository likerRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserAuthenticationService userAuthenticationService;



    public Liker likeTweet(Long tweetId) throws TweetNotFoundException, UserNotFoundException {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet doesn't exist"));
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        if(loggedInUser == null) {
            throw new UserNotFoundException("User not logged in");
        }
        Liker existingLike = likerRepository.findByLikedTweetAndUser(tweet, loggedInUser);
        if(existingLike != null) {
            likerRepository.delete(existingLike);
            return null;
        } else {
            Liker like = new Liker();
            like.setLikedTweet(tweet);
            like.setUser(loggedInUser);
            return likerRepository.save(like);
        }
    }

    public boolean hasUserLikedTweet(Long tweetId, Long userId) {
        System.out.println(tweetId);
        System.out.println(userId);
        return likerRepository.existsByLikedTweetIdAndUserId(tweetId, userId);
    }

    public Liker likeComment(Long commentId, Liker like) throws CommentNotFoundException, UserNotFoundException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment doesn't exist"));
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        if(loggedInUser == null) {
            throw new UserNotFoundException("User not logged in");
        }
        like.setLikedComment(comment);
        like.setUser(loggedInUser);
        return likerRepository.save(like);
    }





}
