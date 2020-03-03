package com.ecommerce.web.service;

import com.ecommerce.web.entity.Good;
import com.ecommerce.web.entity.Recording;
import com.ecommerce.web.exception.NoFindException;

import java.util.List;

public interface RecordingService {
    //提供和交易记录有关的服务

    //通过id查询交易
    Recording search(String id)throws NoFindException ;
    //通过字符串查询交易（物品名字）
    List<Recording> searchFields(String fields)throws NoFindException;
    //新建交易（加入购物车）
    Recording create(int buyer, String good, int num) throws NoFindException;

    //购买（在购物车中的购买）
    Recording buy(String recordingId) throws Exception;
    //购买（不在购物车中的购买）
    Recording buy(int buyId, String goodId, int num) throws Exception;

    //删除（仅限于在购物车中的删除）
    void delete(String id) throws Exception;

    //修改（仅限在购物车中的修改，仅限修改购买数量）
    Recording update(String id, int num) throws Exception;
}
