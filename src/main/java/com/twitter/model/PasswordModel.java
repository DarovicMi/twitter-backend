package com.twitter.model;


public class PasswordModel {

    private String oldPassword;
    private String newPassword;

    private String token;

    public PasswordModel(String newPassword, String token) {
        this.newPassword = newPassword;
        this.token = token;
    }

    public PasswordModel(String newPassword) {
        this.newPassword = newPassword;
    }

    public PasswordModel() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
