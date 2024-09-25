package com.springboot_project.controllers_tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.springboot_project.controllers.GreetingController;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GreetingControllerTest {
    @Autowired
    private GreetingController greetingController;

    @Test
    public void greeting_returnsDefaultGreeting() {
        String result = greetingController.greeting("World");
        assertEquals("Hello, World!", result);
    }
}
