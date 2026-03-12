package com.example.taskmanager.service;

// import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

// import com.example.taskmanager.entity.User;

public interface UserService {
    UserDetailsService userDetailsService();

    String deleteUser(Long id);
    
}
