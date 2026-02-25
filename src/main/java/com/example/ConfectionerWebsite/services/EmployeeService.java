package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.Employee;
import com.example.ConfectionerWebsite.entities.Position;
import com.example.ConfectionerWebsite.exceptions.ResourceNotFoundException;
import com.example.ConfectionerWebsite.repositories.EmployeeRepository;
import com.example.ConfectionerWebsite.repositories.PositionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void updateRoleAndSalary(Long employeeId, Long positionId, Double salary) {
        Employee e = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Сотрудник не найден: " + employeeId));

        Position pos = positionRepository.findById(positionId)
                .orElseThrow(() -> new ResourceNotFoundException("Роль не найдена: " + positionId));

        e.setPosition(pos);
        e.setSalary(salary);
        employeeRepository.save(e);
    }

    public void createEmployee(String fullName, Long positionId, Double salary, String username, String rawPassword) {
        if (employeeRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Такой username уже существует");
        }

        var position = positionRepository.findById(positionId)
                .orElseThrow(() -> new RuntimeException("Роль не найдена"));

        Employee e = new Employee();
        e.setFullName(fullName);
        e.setPosition(position);         // важно: Position, а не String
        e.setSalary(salary);
        e.setUsername(username);
        e.setPassword(passwordEncoder.encode(rawPassword));
        e.setEnabled(true);              // если у тебя есть enabled

        employeeRepository.save(e);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee by id " + id + "not found"));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
