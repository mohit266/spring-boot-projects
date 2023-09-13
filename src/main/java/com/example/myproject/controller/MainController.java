package com.example.myproject.controller;

import com.example.myproject.dto.LoginReqDTO;
import com.example.myproject.dto.LoginResDTO;
import com.example.myproject.models.User;
import com.example.myproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class MainController {

    @Autowired
    private UserService userService;

    // http://localhost:8082/home/user

    @PostMapping("/login")
    public ResponseEntity<LoginResDTO> loginUsers(@RequestBody LoginReqDTO loginReqDTO){
        return userService.login(loginReqDTO);
    }

    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }
}
