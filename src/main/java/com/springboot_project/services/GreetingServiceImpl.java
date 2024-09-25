package com.springboot_project.services;

import org.springframework.stereotype.Service;

/**
 * The implementation of the greeting service.
 */
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
