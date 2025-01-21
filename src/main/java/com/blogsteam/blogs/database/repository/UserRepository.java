package com.blogsteam.blogs.database.repository;

import com.blogsteam.blogs.database.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByName(String name);

    UserEntity findByEmail(String email);


    List<UserEntity> findAll();

}
