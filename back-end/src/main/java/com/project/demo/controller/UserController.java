package com.project.demo.controller;

import com.project.demo.model.Users;
import com.project.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> verifyUser(@RequestBody Users user){
        return ResponseEntity.ok().body(userService.verifyUser(user));
    }

    @PostMapping("/addNewUser")
    public ResponseEntity<?> addNewUser(@RequestBody Users user){
        return ResponseEntity.ok().body(userService.addUser(user));
    }

    @GetMapping("/allUser")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok().body(userService.getUser());
    }

    @PostMapping("/{userName}/assign-role/{roleName}")
    public ResponseEntity<?> assignToUser(@PathVariable String userName, @PathVariable String roleName){
        userService.assignUser(userName,roleName);
        return ResponseEntity.ok("Role assigned Successfully");
    }



}
