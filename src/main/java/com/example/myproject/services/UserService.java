package com.example.myproject.services;

import com.example.myproject.dto.LoginReqDTO;
import com.example.myproject.dto.LoginResDTO;
import com.example.myproject.entity.UserEntity;
import com.example.myproject.repository.UserRepository;
import com.example.myproject.security.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtHelper helper;

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<LoginResDTO> login(LoginReqDTO loginReqDTO){
        try {
            UserEntity userEntity = userRepository.findByUsername(loginReqDTO.getUsername());

            if(userEntity == null || !passwordEncoder.matches(loginReqDTO.getPassword(), userEntity.getPassword())){
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
            }

            log.info("User entity : {}", userEntity);
            String token = this.helper.generateToken(userEntity);
            LoginResDTO loginResDTO = new LoginResDTO();
            loginResDTO.setToken(token);
            loginResDTO.setMessage("Token generated Successfully");
            return ResponseEntity.ok(loginResDTO);
        } catch (Exception e){
            log.error("Exception occurred while login");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
