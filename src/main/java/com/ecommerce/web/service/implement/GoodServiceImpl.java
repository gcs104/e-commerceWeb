package com.ecommerce.web.service.implement;

import com.ecommerce.web.entity.Good;
import com.ecommerce.web.repository.GoodRepository;
import com.ecommerce.web.service.GoodService;
import com.ecommerce.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class GoodServiceImpl implements GoodService {
    GoodRepository goodRepository;
    UserService userService;
    @Autowired
    public GoodServiceImpl(GoodRepository goodRepository, UserService userService) {
        this.goodRepository = goodRepository;
        this.userService = userService;
    }

    @Override
    public Good search(String id) {
        return goodRepository.findById(id).orElse(null);
    }

    @Override
    public Good create(int masterId, String name, BigDecimal price, String notice) {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        Good good = new Good();
        good.setId(uuid);
        good.setName(name);
        good.setMasterId(masterId);
        good.setPrice(price);
        good.setNotice(notice);
        good.setGmtCreated(LocalDateTime.now());
        good.setGmtModifiled(LocalDateTime.now());
        if(userService.updateGoodList(masterId,uuid) == null){
            System.out.println("未成功保存！");
        }
        return goodRepository.save(good);
    }
}
