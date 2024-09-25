package com.springboot_project.services;

public interface GreetingService {
    /**
     * Returns a greeting message with the name.
     * @param name The name to greet.
     * @return The greeting message with the name.
     */
    String greeting(String name);
}
