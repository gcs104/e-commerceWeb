package com.ecommerce.web.controller;

import com.ecommerce.web.model.Good;
import com.ecommerce.web.model.Recording;
import com.ecommerce.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SearchController {
    @Autowired
    User user;
    @Autowired
    Recording recording;
    @Autowired
    Good good;
    @ResponseBody
    @RequestMapping(value = "/test")
    public String test(){
        return user.getAddress()+recording.getBuyer()+good.getName();
    }
}
