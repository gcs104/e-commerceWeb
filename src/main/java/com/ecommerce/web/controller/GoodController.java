package com.ecommerce.web.controller;

import com.ecommerce.web.entity.Good;
import com.ecommerce.web.service.GoodService;
import com.ecommerce.web.util.DisplayUtil;
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
    private DisplayUtil displayUtil;
    @Autowired
    public GoodController(GoodService goodService, DisplayUtil displayUtil) {
        this.goodService = goodService;
        this.displayUtil = displayUtil;
    }

    @GetMapping(value = "/searchGood")
    public Good search(@RequestParam("id")String id){
        try{
            return displayUtil.pack(goodService.search(id));
        }catch (Exception e){e.printStackTrace();}
      return null;
    }

    @PostMapping(value = "/addGood")
    public Good search(@RequestParam("masterId") int masterId, @RequestParam("name") String name,
                       @RequestParam("price")BigDecimal price, @RequestParam("notice")String notice){
        try{
            masterId = displayUtil.unpack(masterId);
            return displayUtil.pack(goodService.create(masterId,name,price,notice));
        }catch (Exception e){e.printStackTrace();}
        return null;
    }
    @PostMapping(value = "/good/update")
    public Good update(@RequestParam("goodId") String id,@RequestParam("name") String name,
                       @RequestParam("price") BigDecimal price,@RequestParam("notice") String notice,
                       @RequestParam("image") String image){
        try{
            Good good = goodService.update(id,name,price,notice,image);
            return displayUtil.pack(good);
        }catch (Exception e){e.printStackTrace();}
        return null;
    }
    @PostMapping(value = "/good/delete")
    public void delete(@RequestParam("goodId") String id){
        try{
            goodService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
