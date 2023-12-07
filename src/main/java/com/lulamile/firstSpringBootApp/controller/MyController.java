package com.lulamile.firstSpringBootApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping(path ="/hello")
    public String helloWorld(){
        return "Hello Lulamile";
    }
}
