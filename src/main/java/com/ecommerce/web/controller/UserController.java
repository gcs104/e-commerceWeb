package com.ecommerce.web.controller;

import com.ecommerce.web.entity.User;
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
    @Autowired
    void setUserService(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "/searchUser")
    public User updateUserTest(@RequestParam("id")String id){
        User user = userService.search(id);
        if(user == null){
            return new User();
        }
        return user;
    }

    @PostMapping(value = "/login")
    public User CreateUserTest(@RequestParam("name") String name,@RequestParam("password") String password,
                               @RequestParam("gender") String gender,@RequestParam("payCode") String payCode){
        User user = userService.create(name, password, gender, payCode);
        if(user == null){
            return new User();
        }
        return user;
    }
}
