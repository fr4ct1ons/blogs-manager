package com.blogsteam.blogs.database.repository;

import com.blogsteam.blogs.database.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByName(String name);

    UserEntity findByEmail(String email);

    List<UserEntity> findAll();

}
