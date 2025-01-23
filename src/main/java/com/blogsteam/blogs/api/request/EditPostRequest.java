package com.blogsteam.blogs.api.request;

public class EditPostRequest {
    public Integer postId;
    public String  newTitle;
    public String newContents;

    public Integer getPostId() {
        return postId;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public String getNewContents() {
        return newContents;
    }
}
