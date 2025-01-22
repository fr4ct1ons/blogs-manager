package com.blogsteam.blogs.api.request;

//this will create a post.
public class CreatePostRequest {
    private Integer userId;
    private String postTitle;
    private String postContents;

    public Integer getUserId() {
        return userId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostContents() {
        return postContents;
    }
}
