package com.ecommerce.web.service;

import com.ecommerce.web.entity.User;
import com.ecommerce.web.exception.InsufficientBalanceException;
import com.ecommerce.web.exception.NoFindException;

import java.math.BigDecimal;


public interface UserService {
    //提供和用户有关的服务

    //通过id查询用户
    User search(int id) throws NoFindException;

    //新建用户
    User create(String name,String password,String gender,String payCode);

//    //更新用户名
//    User updateName(int id, String name)throws NoFindException;

    //更新登录密码
    User updatePassword(int id, String password)throws NoFindException;

    //更新支付密码
    User updatePayCode(int id, String payCode)throws NoFindException;

//    //保存头像路径（图片放在子文件里）
//    User updateProfile(int id, String profile)throws NoFindException;
//
//    //更新地址信息
//    User updateAddress(int id, String address, boolean isAdd) throws Exception;

    //更新出售商品
    User updateGoodList(int id, String goodId)throws Exception;

    //更新余额
    void updateAmount(int id, BigDecimal changeAmount, boolean isAdd) throws Exception;

    //我突然觉得用户名头像地址应该是一起更新的——就像网站里的个人信息更新页面一样。
    User update(int id,String name,String profile,String address) throws Exception;

}
