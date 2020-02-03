package com.ecommerce.web.service;

import com.ecommerce.web.entity.User;

public interface UserService {
    //提供和用户有关的服务

    //通过id查询用户
    User search(String id);

    //新建用户
    User create(String name,String password,String gender,String payCode);
}
