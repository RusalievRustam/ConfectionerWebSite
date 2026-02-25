package com.example.ConfectionerWebsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPagesController {
    @GetMapping("/access-denied")
    public String denied() {
        return "access_denied";
    }
}