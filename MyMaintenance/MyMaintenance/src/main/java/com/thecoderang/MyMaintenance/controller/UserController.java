package com.thecoderang.MyMaintenance.controller;

import com.thecoderang.MyMaintenance.entity.User;
import com.thecoderang.MyMaintenance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user/createEngineer", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> createEngineer(@RequestBody User user) {
        userService.createEngineer(user);
        return new ResponseEntity<>("Engineer created!", HttpStatus.OK);
    }

    @RequestMapping(path = "/user/createAdmin", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> createAdmin(@RequestBody User user) {
        userService.createAdmin(user);
        return new ResponseEntity<>("Admin created!", HttpStatus.OK);
    }
}
