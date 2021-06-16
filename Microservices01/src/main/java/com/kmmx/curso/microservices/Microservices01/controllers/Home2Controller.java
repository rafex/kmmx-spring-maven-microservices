package com.kmmx.curso.microservices.Microservices01.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home2Controller {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/home2")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }
}
