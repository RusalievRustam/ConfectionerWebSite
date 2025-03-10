package com.example.ConfectionerWebsite.services;

import com.example.ConfectionerWebsite.entities.Employee;
import com.example.ConfectionerWebsite.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee by id " + id + "not found"));
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }
}
