package com.project.demo.service;

import com.project.demo.model.Users;
import com.project.demo.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Transactional
    public ResponseEntity addUser(Users user) {
        Users newUser = new Users();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        userRepo.save(newUser);
        return new ResponseEntity<>(201, HttpStatus.CREATED);

    }
}
