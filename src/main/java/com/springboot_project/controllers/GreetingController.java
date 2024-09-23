package com.springboot_project.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springboot_project.services.GreetingService;

@RestController
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
    
    @GetMapping("/greeting")
    public String greeting(@RequestParam(defaultValue = "World") String name) {
        return greetingService.greeting(name);
    }

    @GetMapping("/greeting/{name}")
    public String greetingWithName(@PathVariable String name) {
        return greetingService.greeting(name);
    }   
}
