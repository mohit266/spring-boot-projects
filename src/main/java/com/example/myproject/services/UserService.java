package com.example.myproject.services;

import com.example.myproject.entity.UserEntity;
import com.example.myproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

}
