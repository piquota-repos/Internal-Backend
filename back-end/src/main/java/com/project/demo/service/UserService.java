package com.project.demo.service;

import com.project.demo.model.Role;
import com.project.demo.model.Users;
import com.project.demo.repo.RoleRepo;
import com.project.demo.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

   @Autowired
    private RoleRepo roleRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private  JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public ResponseEntity addUser(Users user) {
        Users newUser = new Users();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(newUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Sign up successful");

    }

    public String verifyUser(Users user) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
       return jwtService.generateToken(user);
    }

    public List<Users> getUser() {
        return userRepo.findAll();
    }


    @Transactional
    public void assignUser(String userName, String role){
        Users user = userRepo.findByUsername(userName);

        Role roles = roleRepository.findByName(role);
                //.orElseThrow(() -> new RuntimeException("Role not found"));

        user.getRoles().add(roles);
        userRepo.save(user);


    }
}
