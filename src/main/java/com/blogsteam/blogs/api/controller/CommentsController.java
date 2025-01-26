package com.blogsteam.blogs.api.controller;

import com.blogsteam.blogs.api.request.CreateCommentRequest;
import com.blogsteam.blogs.api.request.UpdateCommentRequest;
import com.blogsteam.blogs.database.repository.CommentRepository;
import com.blogsteam.blogs.database.repository.PostRepository;
import com.blogsteam.blogs.database.repository.UserRepository;
import com.blogsteam.blogs.database.repository.entity.CommentEntity;
import com.blogsteam.blogs.database.repository.entity.PostEntity;
import com.blogsteam.blogs.database.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/comments")
public class CommentsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping(path = "createComment")
    public ResponseEntity CreateComment(@RequestBody CreateCommentRequest request)
    {
        Optional<UserEntity> userOpt = userRepository.findById(request.getCommentAuthorId());

        if(userOpt.isEmpty())
        {
            return ResponseEntity.status(404).body("No user with ID " + request.getCommentAuthorId() + " was found.");
        }
        UserEntity user = userOpt.get();

        Optional<PostEntity> postOpt = postRepository.findById(request.getPostId());

        if(userOpt.isEmpty())
        {
            return ResponseEntity.status(404).body("No post with ID " + request.getPostId() + " was found.");
        }
        PostEntity post = postOpt.get();

        if(request.getContents().isEmpty())
        {
            return ResponseEntity.status(400).body("Comment contents was empty.");
        }

        CommentEntity entity = new CommentEntity(user, post, request.getContents());

        commentRepository.saveAndFlush(entity);

        return ResponseEntity.ok(entity);
    }

    @PostMapping(path = "updateComment")
    public ResponseEntity updateComment(@RequestBody UpdateCommentRequest request)
    {
        if(request.getNewContents().isEmpty())
        {
            return ResponseEntity.status(400).body("Comment contents was empty.");
        }

        Optional<CommentEntity> commentOpt = commentRepository.findById(request.getCommentId());

        if(commentOpt.isEmpty())
        {
            return ResponseEntity.status(404).body("No comment with ID " + request.getCommentId() + " was found.");
        }
        CommentEntity comment = commentOpt.get();

        comment.setContents(request.getNewContents());
        commentRepository.saveAndFlush(comment);

        return ResponseEntity.ok(comment);
    }

    @DeleteMapping(path = "deleteComment")
    public ResponseEntity deleteComment(@RequestParam Integer id)
    {
        Optional<CommentEntity> commentOpt = commentRepository.findById(id);

        if(commentOpt.isEmpty())
        {
            return ResponseEntity.status(404).body("No comment with ID " + id + " was found.");
        }
        CommentEntity comment = commentOpt.get();

        commentRepository.delete(comment);

        return ResponseEntity.noContent().build();
    }
}
