package com.ecommerce.web.service.implement;

import com.ecommerce.web.entity.Good;
import com.ecommerce.web.entity.User;
import com.ecommerce.web.exception.FindErrorObjectException;
import com.ecommerce.web.exception.NoFindException;
import com.ecommerce.web.repository.GoodRepository;
import com.ecommerce.web.service.GoodService;
import com.ecommerce.web.service.UserService;
import com.ecommerce.web.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GoodServiceImpl implements GoodService {
    private GoodRepository goodRepository;
    private UserService userService;
    private ToolUtil toolUtil;

    @Autowired
    public GoodServiceImpl(GoodRepository goodRepository, UserService userService,ToolUtil toolUtil) {
        this.goodRepository = goodRepository;
        this.userService = userService;
        this.toolUtil = toolUtil;
    }

    @Override
    public Good search(String id) throws NoFindException {
        Good good = goodRepository.findById(id).orElse(null);
        if(good == null){
            throw new NoFindException();
        }
        return good;
    }

    @Override
    public List<Good> searchFields(String fields) throws NoFindException{
        List<Good> goods ;
        goods = goodRepository.findByFieldsInName(fields);
        if(goods == null || goods.isEmpty()){
            throw new NoFindException();
        }
        return goods;
    }

    @Override
    public Good create(int masterId, String name, BigDecimal price, String notice) throws Exception{
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

    @Override
    @Transactional
    public Good update(String id, String name, BigDecimal price, String notice, String image) throws Exception {
        Good good = search(id);
        User user = userService.search(good.getMasterId());

        good.setName(name);
        good.setPrice(price);
        good.setNotice(notice);
        good.setImage(image);
        good.setGmtModifiled(LocalDateTime.now());

        user.setGmtModifiled(LocalDateTime.now());

        return goodRepository.save(good);
    }

    @Override
    @Transactional
    public void delete(String id) throws NoFindException {
        Good good = search(id);
        User user = userService.search(good.getMasterId());
        if(user.getGoodList() == null){
            throw new FindErrorObjectException();
        }
        user.setGoodList(toolUtil.deleteIdFromList(id,user.getGoodList()));
        user.setGmtModifiled(LocalDateTime.now());

        goodRepository.delete(good);
    }
}
