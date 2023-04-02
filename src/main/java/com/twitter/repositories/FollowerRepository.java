package com.twitter.repositories;

import com.twitter.entities.Follower;
import com.twitter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {

List<Follower> findByFollower(User follower);
List<Follower> findByFollowing(User following);
Follower findByFollowerAndFollowing(User follower, User following);


    long countByFollowing(User user);

    long countByFollower(User user);
}
