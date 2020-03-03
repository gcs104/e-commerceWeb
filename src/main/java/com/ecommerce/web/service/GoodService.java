package com.ecommerce.web.service;

import com.ecommerce.web.entity.Good;
import com.ecommerce.web.exception.NoFindException;

import java.math.BigDecimal;
import java.util.List;

public interface GoodService {
    //提供和商品有关的服务

    //通过id查询商品
    Good search(String id) throws NoFindException;
    //通过字符串查询商品,若不存在返回null
    List<Good> searchFields(String fields)throws NoFindException;
    //新建商品
    Good create(int masterId, String name, BigDecimal price, String notice) throws Exception;

    //更新商品（俺寻思商品更新页面应该也是一起的）（和用户信息更新页面一样）(出售者显然不更新)
    //商品更新了会影响未支付的订单，，即更新订单和购买时应该二次核对。TODO
    Good update(String id,String name,BigDecimal price,String notice,String image) throws Exception;

    //商品删除
    //同上。TODO
    void delete(String id)throws NoFindException;
}
