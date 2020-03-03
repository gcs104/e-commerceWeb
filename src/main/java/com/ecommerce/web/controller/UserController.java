package com.ecommerce.web.controller;

import com.ecommerce.web.entity.User;
import com.ecommerce.web.service.UserService;
import com.ecommerce.web.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    //注入用户服务
    private UserService userService;
    private ToolUtil displayUtil;

    @Autowired
    public UserController(UserService userService, ToolUtil displayUtil) {
        this.userService = userService;
        this.displayUtil = displayUtil;
    }

    @GetMapping(value = "/searchUser")
    public User searchUser(@RequestParam("id") int id) {
        id = displayUtil.unpack(id);
        try {
            return displayUtil.pack(userService.search(id));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/login")
    public User createUser(@RequestParam("name") String name, @RequestParam("password") String password,
                           @RequestParam("gender") String gender, @RequestParam("payCode") String payCode) {
        return displayUtil.pack(userService.create(name, password, gender, payCode)) ;
    }

    @PostMapping(value = "/user/update")
    public User updateAdress(@RequestParam("userId") int userId, @RequestParam("name") String name,
                             @RequestParam("profile") String profile, @RequestParam("address") String address) {
        userId = displayUtil.unpack(userId);
        try {
            User user = userService.update(userId, name, profile, address);
            return displayUtil.pack(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

