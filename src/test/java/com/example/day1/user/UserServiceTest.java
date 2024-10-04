package com.example.day1.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Success Case")
    void get() {

        MyUser dummy = new MyUser();
        dummy.setId(1L);
        dummy.setFirstName("Jintadanai");
        dummy.setLastName("Pimpila");
        dummy.setAge(25);
        userRepository.saveAndFlush(dummy);

        UserResponse result = userService.get(1);

        assertEquals(1, result.getId());
        assertEquals("Jintadanai", result.getFirstName());
        assertEquals("Pimpila", result.getLastName());
    }

    @Test
    @DisplayName("Failure Case")
    void getFailure() {
        Exception exception = assertThrows(UserNotFoundException.class, () -> userService.get(2));

        assertEquals("User id = 2 not found.", exception.getMessage());
    }
}