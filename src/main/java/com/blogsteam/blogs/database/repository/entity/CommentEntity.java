package com.blogsteam.blogs.database.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(generator = "Incremental")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private PostEntity post;

    private String contents;

    public CommentEntity() { }

    public CommentEntity(UserEntity author, PostEntity post, String contents) {
        this.author = author;
        this.post = post;
        this.contents = contents;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Integer getId() {
        return id;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public PostEntity getPost() {
        return post;
    }

    public String getContents() {
        return contents;
    }
}
