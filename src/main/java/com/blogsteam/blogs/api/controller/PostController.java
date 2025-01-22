package com.blogsteam.blogs.api.controller;

import com.blogsteam.blogs.api.request.CreatePostRequest;
import com.blogsteam.blogs.database.repository.PostRepository;
import com.blogsteam.blogs.database.repository.UserRepository;
import com.blogsteam.blogs.database.repository.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/posts")
public class PostController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @PostMapping(path = "createPost")
    public ResponseEntity CreatePost(@RequestBody CreatePostRequest request){

        var user = userRepository.findById(request.getUserId());
        if (user.isEmpty())
        {
            return ResponseEntity.badRequest().body("No user was found with the provided ID");
        }

        PostEntity newPost = new PostEntity(user.get(), request.getPostTitle(), request.getPostContents());

        postRepository.save(newPost);

        return ResponseEntity.noContent().build();
    }
}
