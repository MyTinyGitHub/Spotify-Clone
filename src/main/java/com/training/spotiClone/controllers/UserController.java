package com.training.spotiClone.controllers;

import com.training.spotiClone.dao.User;
import com.training.spotiClone.dao.UserType;
import com.training.spotiClone.repositors.list.ListUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController extends BaseController{

    @GetMapping("/users")
    public String getAllUsers() {
        return getUserRepository().toString();
    }

    @PostMapping("/users/register")
    public String addUser(@RequestBody Map<String, String> input) {
        String userName = input.get("userName");
        if(userName == null) {
            return "No username entered";
        }

        String password = input.get("password");
        if(password == null) {
            return "No password provided";
        }

        UserType type = UserType.valueOf(input.get("type"));

        boolean exists = getUserRepository().exists(userName);
        if(exists) {
            return "Such user already exists";
        }

        getUserRepository().add(new User(userName, password, type));
        return "User %s was successfully registered".formatted(userName);
    }
}
