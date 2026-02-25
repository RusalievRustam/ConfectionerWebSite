package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.Employee;
import com.example.ConfectionerWebsite.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Employee e = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String authority = e.getPosition().getName().toLowerCase().trim(); // "директор"

        return org.springframework.security.core.userdetails.User.builder()
                .username(e.getUsername())
                .password(e.getPassword()) // хеш BCrypt
                .disabled(!Boolean.TRUE.equals(e.getEnabled()))
                .authorities(authority) // ВАЖНО: authority, не roles()
                .build();
    }
}