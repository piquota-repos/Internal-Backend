package com.project.demo.model;

import jakarta.persistence.*;

@Entity
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "role_id")
   private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id")
   private Permission permission;
}
