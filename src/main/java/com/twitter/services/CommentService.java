package com.twitter.services;

import com.twitter.entities.Comment;
import com.twitter.entities.Tweet;
import com.twitter.entities.User;
import com.twitter.exceptions.CommentNotFoundException;
import com.twitter.exceptions.TweetNotFoundException;
import com.twitter.exceptions.UserNotFoundException;
import com.twitter.repositories.CommentRepository;
import com.twitter.repositories.TweetRepository;
import com.twitter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    public Comment createTweetComment(Long tweetId, Comment comment) throws TweetNotFoundException {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet doesn't exist"));
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        comment.setTweet(tweet);
        comment.setUser(loggedInUser);
        comment.setReply(false);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    public List<Comment> getTweetComments(Long tweetId) throws TweetNotFoundException {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new TweetNotFoundException("Tweet doesn't exist"));
        return commentRepository.findByTweetAndIsReply(tweet, false);
    }

    public void deleteComment(Long commentId) throws Exception {
        Comment deletedComment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment doesn't exist"));
        User loggedInUser = userAuthenticationService.getLoggedInUser();
        if (deletedComment.getUser() != loggedInUser) {
            throw new Exception("Not authorized to delete this comment");
        }
        commentRepository.delete(deletedComment);
    }

    public void updateComment(Long commentId, Comment comment) throws Exception {
        Comment updatedComment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment doesn't exist"));
        User loggedInUser = userAuthenticationService.getLoggedInUser();

        if (updatedComment.getUser() != loggedInUser) {
            throw new Exception("Not authorized to edit this comment");
        }

        updatedComment.setText(comment.getText());
        updatedComment.setEdited(true);
        updatedComment.setUpdatedAt(LocalDateTime.now());
        commentRepository.save(updatedComment);
    }

    public Comment createReply(Long parentCommentId, User user, String text) throws CommentNotFoundException {
        Comment parentComment = commentRepository.findById(parentCommentId)
                .orElseThrow(() -> new CommentNotFoundException("An unexpected error has happend, comment not found 404"));
        Tweet tweet = parentComment.getTweet();
        Comment reply = new Comment();
        reply.setParentComment(parentComment);
        reply.setUser(user);
        reply.setTweet(tweet);
        reply.setText(text);
        reply.setReply(true);
        reply.setCreatedAt(LocalDateTime.now());
        reply.setUpdatedAt(LocalDateTime.now());
        return commentRepository.save(reply);
    }

public List<Comment> getReplies(Long commentId, boolean isReply) throws CommentNotFoundException {
        Comment parentComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(("Comment doesnt exist")));
        return commentRepository.findByParentCommentAndIsReplyOrderByCreatedAtDesc(parentComment, isReply);
}
    public Long countCommentsByTweetId(Long tweetId) {
        return commentRepository.countByTweetId(tweetId);
    }

}
