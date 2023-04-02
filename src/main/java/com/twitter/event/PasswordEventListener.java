package com.twitter.event;

import com.twitter.entities.User;
import com.twitter.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class PasswordEventListener implements ApplicationListener<PasswordEvent> {

    @Autowired
    private EmailSenderService emailService;

    @Override
    public void onApplicationEvent(PasswordEvent event) {
        User user = event.getUser();
        String url = "http://127.0.0.1:5500/app/main/reset-password" + "/savePassword.html?token=" + event.getPasswordResetToken().getToken();
        emailService.sendEmail(user.getEmail(),"Click the following link to reset your password: \n" + url, "Reset Password");

    }
}
