package com.ecommerce.web.controller;

import com.ecommerce.web.entity.User;
import com.ecommerce.web.model.Config;
import com.ecommerce.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    //注入用户服务
    private UserService userService;
    private Config CONFIG;

    @Autowired
    public UserController(UserService userService, Config CONFIG) {
        this.userService = userService;
        this.CONFIG = CONFIG;
    }

    @GetMapping(value = "/searchUser")
    public User searchUser(@RequestParam("id") int id) {
        try {
            User user = userService.search(id - CONFIG.getUserIdAdd());
            user.setId(user.getId() + CONFIG.getUserIdAdd());
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/login")
    public User createUser(@RequestParam("name") String name, @RequestParam("password") String password,
                           @RequestParam("gender") String gender, @RequestParam("payCode") String payCode) {
        User user = userService.create(name, password, gender, payCode);
        if (user == null) {
            return new User();
        }
        user.setId(user.getId() + CONFIG.getUserIdAdd());
        return user;
    }

    @PostMapping(value = "/updateUser")
    public User updateAdress(@RequestParam("userId") int userId, @RequestParam("name") String name,
                             @RequestParam("profile") String profile, @RequestParam("address") String address) {
        int id = userId - CONFIG.getUserIdAdd();
        try {
            User user = userService.update(id, name, profile, address);
            user.setId(userId);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

