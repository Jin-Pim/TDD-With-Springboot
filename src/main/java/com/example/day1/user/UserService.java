package com.example.day1.user;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse get(int id) {

        Optional<MyUser> result = userRepository.findById((long) id);

        if (result.isEmpty()) {
            throw new UserNotFoundException("User id = " + id + " not found.");
        }

        UserResponse userResponse = new UserResponse();
        userResponse.setId(id);
        userResponse.setFirstName(result.get().getFirstName());
        userResponse.setLastName(result.get().getLastName());
        userResponse.setAge(result.get().getAge());

        return userResponse;
    }

    @Transactional
    public UserResponse post(UserRequest userRequest) {
        try {
            MyUser newUser = new MyUser();
            newUser.setFirstName(userRequest.getFirstName());
            newUser.setLastName(userRequest.getLastName()) ;
            newUser.setAge(userRequest.getAge());

            newUser = userRepository.saveAndFlush(newUser);
//            userRepository.deleteAll();

            UserResponse userResponse = new UserResponse();
            userResponse.setId(Math.toIntExact(newUser.getId()));
            userResponse.setFirstName(newUser.getFirstName());
            userResponse.setLastName(newUser.getLastName());
            userResponse.setAge(newUser.getAge());
            return userResponse;

        } catch (Exception err) {
            throw new RuntimeException(err);
        }

    }
}
