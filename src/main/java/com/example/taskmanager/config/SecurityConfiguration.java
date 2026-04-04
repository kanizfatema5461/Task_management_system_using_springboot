
package com.example.taskmanager.config;


import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.taskmanager.service.JwtService;
// import com.example.taskmanager.service.JwtService;
// import com.example.taskmanager.entity.Role;
import com.example.taskmanager.service.UserService;


@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

        // private final JwtAthenticationFilter jwtAuthenticationFilter;

        // private final UserService userService; 
        
        private final JwtService jwtService;

        @Bean
         public SecurityFilterChain securityFilterChain(HttpSecurity http, UserService userService) throws Exception {
                http
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(request -> request.requestMatchers(
                                        "/api/v1/auth/**",      
                                        // "/api/v1/tasks/**",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/swagger-ui.html",
                                        "/v3/api-docs/**",
                                        "/v3/api-docs.yaml",
                                        "/swagger-resources/**",
                                        "/webjars/**"
                                )
                        .permitAll() 

                                .requestMatchers(HttpMethod.GET, "/api/v1/tasks").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.POST, "/api/v1/tasks").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/tasks").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/tasks").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/auth/users/**").hasAnyRole("ADMIN")


                                // .requestMatchers("/api/v1/admin").hasAnyAuthority(Role.ADMIN.name())
                                // .requestMatchers("/api/v1/user").hasAnyAuthority(Role.USER.name())

                                .requestMatchers("/api/v1/admin").hasRole("ADMIN")
                                .requestMatchers("/api/v1/user").hasRole("USER")

                                .requestMatchers("/api/v1/auth/forgot-password").permitAll()
                                .requestMatchers("/api/v1/auth/verify-otp").permitAll()
                                .requestMatchers("/api/v1/auth/reset-password").permitAll()

                                .anyRequest().authenticated()) 

                                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                // .authenticationProvider(authenticationProvider()).addFilterBefore( new JwtAthenticationFilter(jwtService, userService), UsernamePasswordAuthenticationFilter.class);
                                        // jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
                                .authenticationProvider(authenticationProvider(userService, passwordEncoder()))
                                .addFilterBefore(new JwtAthenticationFilter(jwtService, userService), UsernamePasswordAuthenticationFilter.class);
                        // );

                return http.build(); 
        }

        // @Bean
        // public AuthenticationProvider authenticationProvider() {
        //         DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //         authenticationProvider.setUserDetailsService(userService.userDetailsService());
        //         authenticationProvider.setPasswordEncoder(passwordEncoder());
        //         return authenticationProvider;
        // }

        @Bean
        public AuthenticationProvider authenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
                DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
                authProvider.setUserDetailsService(userService.userDetailsService());
                authProvider.setPasswordEncoder(passwordEncoder);
                return authProvider;
        }


        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
                return config.getAuthenticationManager();
        }

}
