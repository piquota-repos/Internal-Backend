package com.project.demo.controller;

import com.project.demo.model.Users;
import com.project.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class Test {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok().body("Hello World");
    }

    @PostMapping("/addNewUser")
    public ResponseEntity<?> addNewUser(@RequestBody Users user){
        return ResponseEntity.ok().body(userService.addUser(user));
    }



}
