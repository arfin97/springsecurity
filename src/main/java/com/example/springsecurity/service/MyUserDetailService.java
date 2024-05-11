package com.example.springsecurity.service;

import com.example.springsecurity.entity.User;
import com.example.springsecurity.domain.UserPrinciple;
import com.example.springsecurity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {


    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= repo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User 404");
        }

        return new UserPrinciple(user);
    }
}
