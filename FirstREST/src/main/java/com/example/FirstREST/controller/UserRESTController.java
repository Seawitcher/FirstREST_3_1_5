package com.example.FirstREST.controller;


import com.example.FirstREST.model.User;
import com.example.FirstREST.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/")
public class UserRESTController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserRESTController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/userPage")
    public ResponseEntity<User> showAuthUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return new ResponseEntity<> (user, HttpStatus.OK);
    }

}
