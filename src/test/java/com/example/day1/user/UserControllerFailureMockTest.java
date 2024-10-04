package com.example.day1.user;

import com.example.day1.global.ErrorResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerFailureMockTest {

    @MockBean
    private UserService userService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("With mock, Failure with get user by id == 2 => 404 User not found.")
    void getByIdFailure() {

        when(userService.get(2))
                .thenThrow(new UserNotFoundException("User id = 2 not found."));

        ResponseEntity<ErrorResponse> result = restTemplate.getForEntity("/user/2", ErrorResponse.class);

        assertEquals(404, result.getStatusCode().value());
        assertEquals("User id = 2 not found.", Objects.requireNonNull(result.getBody()).getMessage());

    }

}