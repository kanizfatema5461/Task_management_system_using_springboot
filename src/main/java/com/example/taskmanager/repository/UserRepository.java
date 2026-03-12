package com.example.taskmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.entity.Role;
import com.example.taskmanager.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);

    User findByRole(Role role);

    Optional<User> findByVerificationToken(String token);

    void deleteById(Long id);

    boolean existsByEmail(String email);
}
