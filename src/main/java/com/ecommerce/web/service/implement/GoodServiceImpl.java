package com.ecommerce.web.service.implement;

import com.ecommerce.web.entity.Good;
import com.ecommerce.web.repository.GoodRepository;
import com.ecommerce.web.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GoodServiceImpl implements GoodService {
    GoodRepository goodRepository;
    @Autowired
    void setGoodRepository(GoodRepository goodRepository){
        this.goodRepository = goodRepository;
    }

    @Override
    public Good search(int id) {
        return goodRepository.getOne(id);
    }

    @Override
    public Good create(int masterId, String name, BigDecimal price, String notice) {
        Good good = new Good();
        good.setName(name);
        good.setMasterId(masterId);
        good.setPrice(price);
        good.setNotice(notice);
        good.setGmtCreated(LocalDateTime.now());
        good.setGmtModifiled(LocalDateTime.now());
        return goodRepository.save(good);
    }
}
