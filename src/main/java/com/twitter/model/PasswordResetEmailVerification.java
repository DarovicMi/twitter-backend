package com.twitter.model;

public class PasswordResetEmailVerification {
    private String email;

    public PasswordResetEmailVerification(String email) {
        this.email = email;
    }

    public PasswordResetEmailVerification() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
