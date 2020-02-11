package com.ecommerce.web.service;

import com.ecommerce.web.entity.Recording;
import com.ecommerce.web.exception.NoFindException;

public interface RecordingService {
    //提供和交易记录有关的服务

    //通过id查询交易
    Recording search(String id) ;

    //新建交易（加入购物车）
    Recording create(int buyer, String good, int num) throws NoFindException;

    //购买（在购物车中的购买）
    Recording buy(String recordingId) throws Exception;
    //购买（不在购物车中的购买）
    Recording buy(int buyId, String goodId, int num) throws Exception;
}
