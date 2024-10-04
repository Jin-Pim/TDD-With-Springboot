package com.example.day1.hello;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Success with get Hello Spring boot!!")
    void sayHello() {
        HelloResponse result = restTemplate.getForObject("/hello", HelloResponse.class);
        assertEquals("Hello Spring boot!!", result.getMessage());
    }
}