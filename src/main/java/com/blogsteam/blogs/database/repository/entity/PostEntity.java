package com.blogsteam.blogs.database.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(generator = "Incremental")
    private Integer id;

    private String contents;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public PostEntity() {}

    public PostEntity(UserEntity user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

    public Integer getId() {
        return id;
    }

    public String getContents() {
        return contents;
    }

    public String getTitle() {
        return title;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
