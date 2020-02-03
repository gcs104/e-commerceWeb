package com.ecommerce.web.controller;

import com.ecommerce.web.entity.User;
import com.ecommerce.web.repository.UserRepository;
import com.ecommerce.web.service.UserService;
import com.ecommerce.web.service.implement.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @Autowired
    UserRepository userRepository;
    UserService userService = new UserServiceImpl();
    @GetMapping(value = "/UserTest")
    public User updateUserTest(@RequestParam("id")String id){
        User user = userService.search(id);
        if(user == null){
            return new User();
        }
        return user;
    }

    @PostMapping(value = "/UserTest")
    public User CreateUserTest(@RequestParam("name") String name,@RequestParam("password") String password,
                               @RequestParam("gender") String gender,@RequestParam("payCode") String payCode){
        User user = userService.create(name, password, gender, payCode);
        if(user == null){
            return new User();
        }
        return user;
    }
}
