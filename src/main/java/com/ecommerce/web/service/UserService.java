package com.ecommerce.web.service;

import com.ecommerce.web.entity.User;
import com.ecommerce.web.exception.InsufficientBalanceException;

import java.math.BigDecimal;


public interface UserService {
    //提供和用户有关的服务

    //通过id查询用户
    User search(int id);

    //新建用户
    User create(String name,String password,String gender,String payCode);

    //更新用户名
    User updateName(int id,String name);

    //更新登录密码
    User updatePassword(int id,String password);

    //更新支付密码
    User updatePayCode(int id,String payCode);

    //保存头像路径（图片放在子文件里）
    User updateProfile(int id,String profile);

    //更新地址信息
    User updateAddress(int id,String address);

    //更新余额
    User updateAmount(int id, BigDecimal changeAmount) throws InsufficientBalanceException;

}
