package com.blogsteam.blogs.api.controller;

import com.blogsteam.blogs.api.request.CreateUserRequest;
import com.blogsteam.blogs.api.request.EditUserRequest;
import com.blogsteam.blogs.api.request.UserLoginRequest;
import com.blogsteam.blogs.database.repository.UserRepository;
import com.blogsteam.blogs.database.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @PostMapping(path = "loginUser")
    public ResponseEntity loginUser(@RequestBody UserLoginRequest request)
    {

        var userOpt = userRepository.findByEmail(request.getUserEmail());
        if(userOpt.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        var user = userOpt.get();

        if(!user.getPassword().equals(request.getUserPassword()))
        {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping(path = "editUser")
    public ResponseEntity editUser(@RequestBody EditUserRequest request)
    {

        var userOpt = userRepository.findById(request.getUserId());
        if(userOpt.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        var user = userOpt.get();

        if(!request.getNewEmail().isEmpty())
            user.setEmail(request.getNewEmail());

        if(!request.getNewName().isEmpty())
            user.setName(request.getNewName());

        if(!request.getNewPassword().isEmpty())
            user.setPassword(request.getNewPassword());

        userRepository.saveAndFlush(user);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping(path = "deleteUser")
    public ResponseEntity deleteUser(@RequestParam Integer id)
    {
        var userOpt = userRepository.findById(id);

        if(userOpt.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        var user = userOpt.get();

        userRepository.delete(user);

        return ResponseEntity.noContent().build();
    }

}
