package com.example.backend.Repository;

import com.example.backend.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
    User findUserByEmail(String email);
}
