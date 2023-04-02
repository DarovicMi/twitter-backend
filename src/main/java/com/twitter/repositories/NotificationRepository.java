package com.twitter.repositories;

import com.twitter.entities.User;
import com.twitter.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipient(User loggedInUser);

    List<Notification> findByRecipientAndIsRead(User user, boolean b);
}
