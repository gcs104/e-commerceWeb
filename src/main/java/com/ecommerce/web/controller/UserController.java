package com.ecommerce.web.controller;

import com.ecommerce.web.entity.User;
import com.ecommerce.web.exception.NoFindException;
import com.ecommerce.web.service.UserService;
import com.ecommerce.web.util.ConstantUtils;
import com.ecommerce.web.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
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



    @PostMapping(value = "/register")
    public User createUser(@RequestParam("name") String name, @RequestParam("password") String password,
                           @RequestParam("gender") String gender, @RequestParam("payCode") String payCode) {
        return displayUtil.pack(userService.create(name, password, gender, payCode)) ;
    }



    @PostMapping("/login")
    public Object login( @RequestParam("id") int id,@RequestParam("password") String password,HttpServletRequest request) throws JSONException, NoFindException {
        if (userService.login(id, password)) {

            //如果成功了，聚合需要返回的信息

            User user =userService.search(id);
            request.getSession().setAttribute(ConstantUtils.USER_SESSION_KEY,user);
            return displayUtil.result(displayUtil.secret(user),"登录成功");

        }
        else
            return displayUtil.result(null,"登录失败");
    }




    @PostMapping("/logout")
    public Object logout(HttpServletRequest request){
        request.getSession().removeAttribute(ConstantUtils.USER_SESSION_KEY);
        return displayUtil.result(null,"退出成功");
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

