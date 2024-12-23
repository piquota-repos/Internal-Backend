package com.project.demo.repo;

import com.project.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);

}
