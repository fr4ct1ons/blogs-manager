package com.blogsteam.blogs.api.request;

public class EditUserRequest {
    public Integer userId;
    public String newName;
    private String newEmail;
    private String newPassword;

    public Integer getUserId() {
        return userId;
    }

    public String getNewName() {
        return newName;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
