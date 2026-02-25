package com.example.ConfectionerWebsite.config;

import com.example.ConfectionerWebsite.entities.Employee;
import com.example.ConfectionerWebsite.entities.Position;
import com.example.ConfectionerWebsite.repositories.EmployeeRepository;
import com.example.ConfectionerWebsite.repositories.PositionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitUsers {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final PasswordEncoder encoder;

    @PostConstruct
    public void init() {
        if (employeeRepository.findByUsername("director").isPresent()) return;

        Position director = positionRepository.findByName("Директор")
                .orElseThrow();

        Employee e = new Employee();
        e.setUsername("director");
        e.setPassword(encoder.encode("director123"));
        e.setEnabled(true);
        e.setFullName("Директор");
        e.setPosition(director);
        e.setSalary(0.0);

        employeeRepository.save(e);
    }
}