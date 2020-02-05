package com.ecommerce.web.service;

import com.ecommerce.web.entity.Good;
import com.ecommerce.web.entity.User;

import java.math.BigDecimal;

public interface GoodService {
    //提供和商品有关的服务

    //通过id查询商品
    Good search(int id);

    //新建商品
    Good create(int masterId, String name, BigDecimal price,String notice);

}
