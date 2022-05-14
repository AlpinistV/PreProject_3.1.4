package com.restjs.controller;

import com.restjs.entity.User;
import com.restjs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminControllerRest {
    private final UserService userService;

    @Autowired
    public AdminControllerRest(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(ModelMap model) throws SQLException {
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> insertUser(@RequestBody User user) throws SQLException {
        userService.addUser(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/edit/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") long id) throws SQLException {
        return userService.getUserById(id);
    }

    @PutMapping("/edit")
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user) throws SQLException {
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUserById(@RequestBody @Valid User user) throws SQLException {
        userService.deleteUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
