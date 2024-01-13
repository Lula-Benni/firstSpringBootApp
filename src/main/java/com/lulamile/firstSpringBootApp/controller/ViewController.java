package com.lulamile.firstSpringBootApp.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
    @GetMapping("/home")
    public String home(Model model){
        return "home";
    }
}
