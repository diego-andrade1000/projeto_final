package com.example.backend.Controller;

import com.example.backend.Domain.User;
import com.example.backend.Exception.GenericHTTPException;
import com.example.backend.Repository.UserRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Status;

import javax.validation.Valid;

import static java.util.regex.Pattern.matches;


/**
     * Controller to authenticate users.
     */
    @RestController
    @Transactional
    @RequestMapping("/api")
    @EnableAutoConfiguration
    @ComponentScan
    public class UserJWTController {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        public UserJWTController(
                UserRepository userRepository,
                PasswordEncoder passwordEncoder
                ) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
        }


        @PostMapping("/authenticate")
        public ResponseEntity<User> authorize(@RequestBody User loginVM) {
            User user;
            try{
                user = userRepository.findUserByEmail(loginVM.getEmail());
                passwordEncoder.matches(loginVM.getPassword(), user.getPassword());
            }catch (Exception e){
                throw new GenericHTTPException("Email incorreto ou senha incorretos.", Status.UNAUTHORIZED);
            }
            return ResponseEntity.ok(user);
        }



}
