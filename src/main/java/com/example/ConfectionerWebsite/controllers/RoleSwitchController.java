package com.example.ConfectionerWebsite.controllers;

import com.example.ConfectionerWebsite.entities.Employee;
import com.example.ConfectionerWebsite.entities.Position;
import com.example.ConfectionerWebsite.repositories.EmployeeRepository;
import com.example.ConfectionerWebsite.repositories.PositionRepository;
import com.example.ConfectionerWebsite.services.DbUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class RoleSwitchController {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final DbUserDetailsService userDetailsService;

    @PostMapping("/me/role")
    public String changeMyRole(@RequestParam Long positionId) {

        var auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // Разрешаем только админу
        if (auth.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("админ"))) {
            return "redirect:/access-denied";
        }

        Employee employee = employeeRepository.findByUsername(username).orElseThrow();
        Position position = positionRepository.findById(positionId).orElseThrow();
        employee.setPosition(position);
        employeeRepository.save(employee);

        // Обновляем authorities в текущей сессии
        UserDetails ud = userDetailsService.loadUserByUsername(username);
        var newAuth = new UsernamePasswordAuthenticationToken(
                ud, auth.getCredentials(), ud.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        return "redirect:/";
    }
}