package com.training.spotiClone.controllers;

import com.training.spotiClone.dao.User;
import com.training.spotiClone.repositors.list.ListUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController{

    @GetMapping("/users")
    public String getAllUsers() {
        return getUserRepository().toString();
    }

    @PostMapping("/users/register")
    public String addUser(@RequestBody User user) {
        getUserRepository().add(user);
        return "User %s was successfully registered".formatted(user.getName());
    }
}
