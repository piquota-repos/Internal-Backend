package com.project.demo.repo;

import com.project.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Role findByName(String roleAdmin);
}
