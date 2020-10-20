package com.Infrrd.api.controller;
import com.Infrrd.api.model.User;
import com.Infrrd.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
@RequestMapping("/user-api")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping(value = "/users", produces = "application/json")
    public CompletableFuture<ResponseEntity> findAllUsers() {
        return  userService.findAllUsers().thenApply(ResponseEntity::ok);
    }


    @GetMapping(value = "/getUsers", produces = "application/json")
    public  ResponseEntity getUsers(){
        CompletableFuture<List<User>> users1= userService.findAllUsers();
        CompletableFuture<List<User>> users2= userService.findAllUsers();
        CompletableFuture.allOf(users1,users2).join();
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}
