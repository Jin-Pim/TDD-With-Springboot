package com.example.day1.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

    @Mock
    private UserRepository mockUserRepository;

    @Test
    @DisplayName("Get user with find by id 1 - Success")
    void getUser() {
        // Arrange
        MyUser u1 = new MyUser();
        u1.setId(1L);
        u1.setFirstName("Anakin");
        u1.setLastName("Skywalker");
        u1.setAge(28);

        when(mockUserRepository.findById(1L)).thenReturn(Optional.of(u1));

        UserService service = new UserService(mockUserRepository);

        // Act
        UserResponse result = service.get(1);

        // Assert
        assertEquals(1, result.getId());
        assertEquals("Anakin", result.getFirstName());
        assertEquals("Skywalker", result.getLastName());
        assertEquals(28, result.getAge());
    }
}