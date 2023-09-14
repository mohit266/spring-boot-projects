package com.example.myproject.controller;

import com.example.myproject.entity.UserEntity;
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

    // http://localhost:8082/home/all-users

    @Autowired
    UserService userService;

    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }

    @GetMapping("/all-users")
    public List<UserEntity> getAllUsers(){
        return userService.getUsers();
    }
}
