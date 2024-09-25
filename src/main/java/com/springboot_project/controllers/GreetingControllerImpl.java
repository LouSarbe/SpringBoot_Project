package com.springboot_project.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot_project.services.GreetingService;

/**
 * Implementation of GreetingController.
 */
@RestController
@RequestMapping("/api/greeting")
public class GreetingControllerImpl implements GreetingController {

    private final GreetingService greetingService;

    public GreetingControllerImpl(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
    
    @Override
    @GetMapping("/")
    public String greeting() {
        return greetingService.greeting("World");
    }

    @Override
    @GetMapping("/{name}")
    public String greeting(@PathVariable String name) {
        return greetingService.greeting(name);
    }   
}
