package com.myfab.pocciti.controller;


import com.myfab.pocciti.exception.UserNotFoundException;
import com.myfab.pocciti.model.User;
import com.myfab.pocciti.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public Collection<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable String id) {
        return this.userService.getAllUsersById(id).orElseThrow(UserNotFoundException::new);
    }

    @PostMapping(path = "/update/{id}")
    public String updateDepartmentById(@PathVariable String id,
                                       @RequestBody Map<String, String> body) {
        final String firstName = body.get("firstname");
        final String lastName = body.get("lastname");
        if (firstName == null || id.isEmpty() || lastName == null)
            throw new RuntimeException("Id,firstname or lastname is missing!");
        return this.userService.updateUser(id, firstName, lastName);
    }

}
