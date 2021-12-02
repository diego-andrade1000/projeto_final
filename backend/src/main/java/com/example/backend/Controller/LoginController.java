package com.example.backend.Controller;

import com.example.backend.Domain.User;
import com.example.backend.Exception.GenericHTTPException;
import com.example.backend.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Status;



/**
     * Controller to authenticate users.
     */
    @RestController
    @RequestMapping("/api")
    public class LoginController {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        public LoginController(
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
