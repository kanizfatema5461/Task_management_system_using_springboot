package com.example.taskmanager.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

// import org.apache.catalina.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
// import jakarta.persistence.Column;
// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements  UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(unique = true)
    private String firstname;
    private String lastname;

    @Column(unique = true)
    private String email;
    private String password;

    // @Enumerated(EnumType.STRING)
    private Role role;

    // private String verificationToken;

    // @Column(nullable = false)
    private Boolean enabled;

    private Boolean isDeleted = false;

    @Column(nullable = false)
    private boolean emailVerified = false;

    @Column(name = "otp")
    private String otp;

    @Column(name = "otp_expiry_time")
    private LocalDateTime otpExpiryTime;





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(enabled);
    }


    
    public boolean isEmailVerified() {
    return emailVerified;
    }


    // @Override
    // public boolean isEnabled() {
    //     return true;
    // } 



    // Getters and Setters
}
