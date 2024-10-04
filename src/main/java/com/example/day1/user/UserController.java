package com.example.day1.user;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import static io.micrometer.common.util.StringUtils.isEmpty;


@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/{id}")
    public UserResponse getById(@PathVariable int id) {
        return userService.get(id);
    }

    @PostMapping("/user")
    public UserResponse createNewUser(@Valid @RequestBody UserRequest userRequest) {
        System.out.println(userRequest);
        return userService.post(userRequest);
    }
}
