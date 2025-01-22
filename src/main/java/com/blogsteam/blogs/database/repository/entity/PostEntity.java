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
}
