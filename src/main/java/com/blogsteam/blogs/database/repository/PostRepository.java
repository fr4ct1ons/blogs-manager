package com.blogsteam.blogs.database.repository;

import com.blogsteam.blogs.database.repository.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

}
