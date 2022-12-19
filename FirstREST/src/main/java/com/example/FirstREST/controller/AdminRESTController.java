package com.example.FirstREST.controller;


import com.example.FirstREST.model.User;
import com.example.FirstREST.service.RoleService;
import com.example.FirstREST.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/admin")
public class AdminRESTController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminRESTController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    public ResponseEntity<List<User>> showUsers() {
        List <User> listAllUsers = userService.getList();
        return new ResponseEntity<>(listAllUsers, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        return new ResponseEntity<> (user, HttpStatus.OK);
    }

    @GetMapping("/userAuth")
    public ResponseEntity<User> showAuthUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return new ResponseEntity<> (user, HttpStatus.OK);
    }

    @PostMapping("/newAddUser")
    public ResponseEntity<HttpStatus> saveNewUser(
            @RequestBody User user
            ) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.add(user);
        return new ResponseEntity<> (HttpStatus.OK);
    }

    @DeleteMapping("{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


    @PatchMapping("/editUser/{id}")
    public ResponseEntity<HttpStatus> userSaveEdit(@RequestBody User user, @PathVariable("id") Long id) {
        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.editUser(user);
        return new ResponseEntity<> (HttpStatus.OK);
    }

}
