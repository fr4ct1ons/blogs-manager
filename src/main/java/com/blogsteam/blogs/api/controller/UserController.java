package com.blogsteam.blogs.api.controller;

import com.blogsteam.blogs.api.request.CreateUserRequest;
import com.blogsteam.blogs.database.repository.UserRepository;
import com.blogsteam.blogs.database.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "getUser")
    public ResponseEntity getUser(@RequestParam Integer id)
    {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(user.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User not found!");

    }

    @PostMapping(path = "createUser")
    public ResponseEntity createUser(@RequestBody CreateUserRequest request)
    {
        var existing = userRepository.findByEmail(request.getEmail());
        if(existing != null)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with email" + request.getEmail() + "already exists!");
        }

        UserEntity newUser = new UserEntity(request.getPassword(), request.getEmail(), request.getName());

        newUser = userRepository.save(newUser);

        return ResponseEntity.ok(newUser);
    }


}
