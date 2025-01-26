package com.blogsteam.blogs.database.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(generator = "Incremental")
    private Integer id;

    private String contents;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<CommentEntity> comments = new ArrayList<>();

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

    public List<CommentEntity> getComments() { return comments; }
}
