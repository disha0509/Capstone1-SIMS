package com.inven.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF (optional for APIs)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/register", "/api/users/login","/api/inventory/**","/api/alerts/**","/api/reports/**").permitAll() // Allow public endpoints
                .requestMatchers("/index.html","/inventory.html","/admin.html","/aboutus.html","/alerts.html","/inventorymanage.html","report.html").permitAll()
                .requestMatchers("/css/**", "/js/**").permitAll()
                .anyRequest().authenticated() // Restrict other endpoints
            )
            .formLogin().disable(); // Disable default login page
            

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // For securely hashing passwords
    }
}
