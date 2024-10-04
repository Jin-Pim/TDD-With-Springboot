package com.example.day1.user;

import com.example.day1.global.ErrorResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerFailureTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Failure with get user by id == 2 => 404 User not found.")
    void getByIdFailure() {
        ResponseEntity<ErrorResponse> result = restTemplate.getForEntity("/user/2", ErrorResponse.class);

        assertEquals(404, result.getStatusCode().value());
        assertEquals("User id = 2 not found.", Objects.requireNonNull(result.getBody()).getMessage());
    }

    @Test
    @DisplayName("Failure with get user by id == aaa => Error input type mismatch")
    void getByIdWrongArgumentType() {
        ResponseEntity<ErrorResponse> result = restTemplate.getForEntity("/user/aaa", ErrorResponse.class);

        assertEquals(400, result.getStatusCode().value());
        assertEquals("Failed to convert value of type 'java.lang.String' to required type 'int'; For input string: \"aaa\"", Objects.requireNonNull(result.getBody()).getMessage());
    }

    @Test
    @DisplayName("Failure with create new user firstName is blank")
    void createNewUserWithBlankFirstName() {

        UserRequest newUser = new UserRequest();
        newUser.setFirstName("");
        newUser.setLastName("Kenobi");
        newUser.setAge(45);

        ResponseEntity<ErrorResponse> result = restTemplate.postForEntity("/user", newUser, ErrorResponse.class);

        assertEquals(400, result.getStatusCode().value());
    }

    @Test
    @DisplayName("Failure with create new user lastName is blank")
    void createNewUserWithBlankLastName() {

        UserRequest newUser = new UserRequest();
        newUser.setFirstName("Obi-one");
        newUser.setLastName("");
        newUser.setAge(45);

        ResponseEntity<ErrorResponse> result = restTemplate.postForEntity("/user", newUser, ErrorResponse.class);

        assertEquals(400, result.getStatusCode().value());
    }
}