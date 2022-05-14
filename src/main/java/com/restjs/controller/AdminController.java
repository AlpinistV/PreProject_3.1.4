package com.restjs.controller;

import com.restjs.entity.Role;
import com.restjs.entity.User;
import com.restjs.repository.RoleRepository;
import com.restjs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }


    @GetMapping("/select")
    public String getUsers(ModelMap model) throws SQLException {
        model.addAttribute("user1", new User());
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(1L, "ADMIN"));
        roleSet.add(new Role(2L, "USER"));
        model.addAttribute("roleSet", roleSet);
        return "select";
    }
}
