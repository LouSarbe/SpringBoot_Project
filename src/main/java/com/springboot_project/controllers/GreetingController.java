package com.springboot_project.controllers;

/**
 * Greeting controller contract.
 */
public interface GreetingController {
    /**
     * Returns a greeting message.
     * @return The greeting message.
     */
    String greeting();

    /**
     * Returns a greeting message with the name.
     * @param name The name to greet.
     * @return The greeting message with the name.
     */
    String greeting(String name);
}
