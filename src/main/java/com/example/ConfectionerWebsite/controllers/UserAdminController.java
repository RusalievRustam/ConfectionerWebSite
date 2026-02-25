package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.entities.Employee;
import com.example.ConfectionerWebsite.entities.Position;
import com.example.ConfectionerWebsite.repositories.EmployeeRepository;
import com.example.ConfectionerWebsite.repositories.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserAdminController {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/users/create")
    public String createUserForm(Model model) {
        model.addAttribute("positions", positionRepository.findAll());
        return "create_user";
    }

    @PostMapping("/users/create")
    public String createUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String fullName,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String address,
            @RequestParam Long positionId,
            @RequestParam Double salary,
            RedirectAttributes ra
    ) {
        if (employeeRepository.findByUsername(username).isPresent()) {
            ra.addFlashAttribute("errorMessage", "Логин уже занят: " + username);
            ra.addFlashAttribute("username", username);
            ra.addFlashAttribute("fullName", fullName);
            ra.addFlashAttribute("phone", phone);
            ra.addFlashAttribute("address", address);
            ra.addFlashAttribute("positionId", positionId);
            ra.addFlashAttribute("salary", salary);
            return "redirect:/users/create";
        }

        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new IllegalArgumentException("Роль не найдена"));

        Employee e = new Employee();
        e.setUsername(username);
        e.setPassword(passwordEncoder.encode(password));
        e.setFullName(fullName);
        e.setPhone(phone);
        e.setAddress(address);
        e.setSalary(salary);
        e.setPosition(position);
        e.setEnabled(true); // если поле enabled есть в БД

        employeeRepository.save(e);

        ra.addFlashAttribute("successMessage", "Пользователь создан: " + username);
        return "redirect:/employees";
    }
}