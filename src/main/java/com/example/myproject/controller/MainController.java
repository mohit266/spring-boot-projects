package com.example.myproject.controller;

import com.example.myproject.models.User;
import com.example.myproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class MainController {

    @Autowired
    private UserService userService;

    // http://localhost:8082/home/user
    @GetMapping("/user")
    public List<User> getUser(){
        System.out.println("Getting user");
        return userService.getUsers();
    }

    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }


}
