package com.ecommerce.web.service;

import com.ecommerce.web.entity.Recording;
import com.ecommerce.web.entity.User;
import com.ecommerce.web.exception.InsufficientBalanceException;
import com.ecommerce.web.exception.NoFindException;

import java.math.BigDecimal;

public interface RecordingService {
    //提供和交易记录有关的服务

    //通过id查询交易
    Recording search(int id) ;

    //新建交易（加入购物车）
    Recording create(int buyer, int good, int num) throws NoFindException;

    //购买（在购物车中的购买）
    Recording buy(int recordingId) throws Exception;
    //购买（不在购物车中的购买）
    Recording buy(int buyId,int goodId,int num) throws Exception;
}
