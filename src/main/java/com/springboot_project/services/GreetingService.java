package com.springboot_project.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String greeting(String name) {
        return String.format("Hello, %s!", name);
    }
}
