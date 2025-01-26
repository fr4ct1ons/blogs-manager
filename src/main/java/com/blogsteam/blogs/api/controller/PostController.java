package com.blogsteam.blogs.api.controller;

import com.blogsteam.blogs.api.request.CreatePostRequest;
import com.blogsteam.blogs.api.request.EditPostRequest;
import com.blogsteam.blogs.database.repository.CommentRepository;
import com.blogsteam.blogs.database.repository.PostRepository;
import com.blogsteam.blogs.database.repository.UserRepository;
import com.blogsteam.blogs.database.repository.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/posts")
public class PostController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping(path = "createPost")
    public ResponseEntity CreatePost(@RequestBody CreatePostRequest request){

        var user = userRepository.findById(request.getUserId());
        if (user.isEmpty())
        {
            return ResponseEntity.badRequest().body("No user was found with the provided ID");
        }

        PostEntity newPost = new PostEntity(user.get(), request.getPostTitle(), request.getPostContents());

        postRepository.save(newPost);

        return ResponseEntity.ok(newPost);
    }

    @PostMapping(path = "editPost")
    public ResponseEntity EditPost(@RequestBody EditPostRequest request)
    {
        var optionalPost = postRepository.findById(request.getPostId());
        if (optionalPost.isEmpty())
        {
            return ResponseEntity.badRequest().body("No post was found with the provided ID");
        }
        var post = optionalPost.get();
        if(!request.getNewTitle().equals(post.getTitle()))
        {
            post.setTitle(request.getNewTitle());
        }

        if(!request.getNewContents().equals(post.getContents()))
        {
            post.setContents(request.getNewContents());
        }

        postRepository.saveAndFlush(post);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping(path = "deletePost")
    public ResponseEntity DeletePost(@RequestParam Integer id)
    {
        var optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty())
        {
            return ResponseEntity.badRequest().body("No post was found with the provided ID");
        }
        var post = optionalPost.get();

        var list = post.getComments();
        for (int i = 0; i < list.size(); i++) {
            var comment = list.get(i);
            commentRepository.delete(comment);
        }
        postRepository.delete(post);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "getPost")
    public ResponseEntity GetPost(@RequestParam Integer id)
    {
        var optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty())
        {
            return ResponseEntity.badRequest().body("No post was found with the provided ID");
        }
        var post = optionalPost.get();

        return ResponseEntity.ok(post);
    }
}
