package com.example.springsecurity.controller;

import com.example.springsecurity.entity.User;
import com.example.springsecurity.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        return "Hello World" + request.getSession().getId();
    }

    @GetMapping("/about")
    public String about() {
        return "Welcome to about";
    }

    @GetMapping("/register")
    public String register(){
        return "Welcome to register";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            if(authentication.isAuthenticated()) {
                return jwtService.getJwtToken(user.getUsername());
            } else {
                return "User not logged in";
            }
        } catch (Exception e){
            return "Invalid username or password";
        }
    }
}
