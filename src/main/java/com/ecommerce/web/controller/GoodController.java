package com.ecommerce.web.controller;

import com.ecommerce.web.entity.Good;
import com.ecommerce.web.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class GoodController {
    //注入商品服务
    private GoodService goodService;
    @Autowired
    void setGoodService(GoodService goodService){
        this.goodService = goodService;
    }

    @GetMapping(value = "/searchGood")
    public Good search(@RequestParam("id")String id){
        return goodService.search(id);
    }

    @PostMapping(value = "/addGood")
    public Good search(@RequestParam("masterId") int masterId, @RequestParam("name") String name,
                       @RequestParam("price")BigDecimal price, @RequestParam("notice")String notice){
        return goodService.create(masterId,name,price,notice);
    }
}
