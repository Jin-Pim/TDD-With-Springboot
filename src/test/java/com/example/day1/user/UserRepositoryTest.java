package com.example.day1.user;

import org.h2.engine.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Found user id = 1")
    public void getFoundUser() {

        MyUser dummy = new MyUser();
        dummy.setId(1L);
        dummy.setFirstName("Jintadanai");
        dummy.setLastName("Pimpila");
        dummy.setAge(25);
        userRepository.saveAndFlush(dummy);

        Optional<MyUser> result = userRepository.findById(1L);

        assertEquals(1, result.get().getId());
        assertEquals("Jintadanai", result.get().getFirstName());
        assertEquals("Pimpila", result.get().getLastName());
        assertEquals(25, result.get().getAge());
    }

}