package com.myfab.pocciti.service;

import com.myfab.pocciti.exception.UserNotFoundException;
import com.myfab.pocciti.model.User;
import com.myfab.pocciti.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public Optional<User> getAllUsersById(final String id) {
        return this.userRepository.findById(id);
    }

    public String updateUser(final String id, final String firstname, final String lastname) {
        boolean isUserExists = this.userRepository.existsById(id);
        if (!isUserExists) {
            throw new UserNotFoundException();
        }
        if (this.userRepository.updateUserName(id, firstname, lastname) > 0)
            return "Update successful!";
        else
            throw new RuntimeException("Update operation failed!");
    }
}
