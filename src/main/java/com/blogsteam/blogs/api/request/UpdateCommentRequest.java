package com.blogsteam.blogs.api.request;

public class UpdateCommentRequest {
    private String newContents;
    private Integer commentId;

    public String getNewContents() {
        return newContents;
    }

    public Integer getCommentId() {
        return commentId;
    }
}
