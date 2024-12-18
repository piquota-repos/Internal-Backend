package com.project.demo.service;

import com.project.demo.model.UserPrincipal;
import com.project.demo.model.Users;
import com.project.demo.repo.UserRepo;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service()
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println(username);
        Users users = userRepo.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(users);
    }
}
