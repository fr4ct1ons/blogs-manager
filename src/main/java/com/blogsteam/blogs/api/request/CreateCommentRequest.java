package com.blogsteam.blogs.api.request;

public class CreateCommentRequest {
    private Integer commentAuthorId;
    private Integer postId;
    private String contents;

    public Integer getCommentAuthorId() {
        return commentAuthorId;
    }

    public Integer getPostId() {
        return postId;
    }

    public String getContents() {
        return contents;
    }
}
