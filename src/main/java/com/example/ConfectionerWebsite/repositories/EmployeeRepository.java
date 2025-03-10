package com.example.ConfectionerWebsite.repositories;

import com.example.ConfectionerWebsite.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
