package com.ecommerce.web.service;

import com.ecommerce.web.entity.Recording;
import com.ecommerce.web.entity.User;

import java.math.BigDecimal;

public interface RecordingService {
    //提供和交易记录有关的服务

    //通过id查询交易
    Recording search(int id);

    //新建交易
    Recording create(int buyer, int good, int num);
}
