package com.example.day1.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerSuccessTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Success with get user by id == 1")
    void getById() {

        MyUser dummy = new MyUser();
        dummy.setId(1L);
        dummy.setFirstName("Jintadanai");
        dummy.setLastName("Pimpila");
        dummy.setAge(25);
        userRepository.saveAndFlush(dummy);

        UserResponse result = restTemplate.getForObject("/user/1", UserResponse.class);

        assertEquals(1, result.getId());
        assertEquals("Jintadanai", result.getFirstName());
        assertEquals("Pimpila", result.getLastName());
        assertEquals(25, result.getAge());
    }

    @Test
    @DisplayName("Success with create new user")
    void createNewUser() {

        UserRequest newUser = new UserRequest();
        newUser.setFirstName("Obi-one");
        newUser.setLastName("Kenobi");
        newUser.setAge(45);

        UserResponse result = restTemplate.postForObject("/user", newUser, UserResponse.class);

        assertEquals(1, result.getId());
        assertEquals("Obi-one", result.getFirstName());
        assertEquals("Kenobi", result.getLastName());
        assertEquals(45, result.getAge());
    }
}