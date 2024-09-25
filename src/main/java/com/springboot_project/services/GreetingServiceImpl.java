package com.springboot_project.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {

    /**
     * {@inheritDoc}
     */
    @Override
    public String greeting(String name) {
        return String.format("Hello, %s!", name);
    }
}
