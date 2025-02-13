package com.blogsteam.blogs.database.repository.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "Incremental")
    private Integer id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<PostEntity> posts = new ArrayList<>();

    public UserEntity(){}

    public UserEntity(String password, String email, String name, Integer id) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.id = id;
    }

    public UserEntity(String password, String email, String name) {
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PostEntity> getPosts() { return posts; }
}
