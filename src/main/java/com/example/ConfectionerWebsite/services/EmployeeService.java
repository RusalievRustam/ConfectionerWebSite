package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.Employee;
import com.example.ConfectionerWebsite.entities.Position;
import com.example.ConfectionerWebsite.exceptions.ResourceNotFoundException;
import com.example.ConfectionerWebsite.repositories.EmployeeRepository;
import com.example.ConfectionerWebsite.repositories.PositionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

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

    public Employee createEmployee(String fullName, Long positionId, Double salary, String address, String phone) {
        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new ResourceNotFoundException("Роль не найдена " + positionId));
        Employee employee = new Employee();
        employee.setFullName(fullName);
        employee.setPosition(position);
        employee.setSalary(salary);
        employee.setAddress(address);
        employee.setPhone(phone);
        return employeeRepository.save(employee);
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
