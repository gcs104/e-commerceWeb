package com.ecommerce.web.controller;

import com.ecommerce.web.entity.Good;
import com.ecommerce.web.entity.Recording;
import com.ecommerce.web.entity.User;
import com.ecommerce.web.model.Config;
import com.ecommerce.web.repository.GoodRepository;
import com.ecommerce.web.repository.RecordingRepository;
import com.ecommerce.web.repository.UserRepository;
import com.ecommerce.web.util.DisplayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class TestController {
    DisplayUtil displayUtil;


    UserRepository userRepository ;
//    @Autowired
//    void setUserRepository(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }
    GoodRepository goodRepository ;
//    @Autowired
//    void setGoodRepository(GoodRepository goodRepository){
//        this.goodRepository = goodRepository;
//    }
    RecordingRepository recordingRepository;
//    @Autowired
//    void setRecordingRepository(RecordingRepository recordingRepository){this.recordingRepository = recordingRepository;}
    @Autowired
    public TestController(DisplayUtil displayUtil, UserRepository userRepository, GoodRepository goodRepository, RecordingRepository recordingRepository) {
        this.displayUtil = displayUtil;
        this.userRepository = userRepository;
        this.goodRepository = goodRepository;
        this.recordingRepository = recordingRepository;
    }

    @PostMapping("/init")
    public void init(){
        System.out.println("开始初始化数据");
        String recordingId = UUID.randomUUID().toString().replaceAll("-","");
        String goodId = UUID.randomUUID().toString().replaceAll("-","");
        User user = initUser(recordingId);
        initGood(goodId,user.getId());
        initRecord(recordingId,goodId,user.getId());
    }
    public User initUser( String recordingId){
        System.out.println("初始化用户测试数据");
        User user = new User();
        user.setName("我是一个人");
        user.setGmtCreated(LocalDateTime.now());
        user.setGmtModifiled(LocalDateTime.now());
        user.setProfile("http://5b0988e595225.cdn.sohucs.com/q_70,c_zoom,w_640/images/20181214/6e00a9e09e8d4be8af46ebcd6e44a9db.jpeg");
        user.setPassword("123456");
        user.setAmount(new BigDecimal(5000));
        user.setGender("male");
        user.setPayCode("330226");
        user.setAddress("地址1;地址2");
        //user.setRecording("");
        user.setShopping(recordingId);
        return userRepository.save(user);
    }

    public void initGood(String goodId, int userId){
        System.out.println("初始化商品测试数据");
        Good good = new Good();
        good.setId(goodId);
        good.setGmtCreated(LocalDateTime.of(2020,1,1,12,30));
        good.setGmtModifiled(LocalDateTime.now());
        good.setMasterId(userId);
        good.setName("筷子");
        good.setPrice(new BigDecimal(10));
        good.setNotice("无");
        goodRepository.save(good);

    }

    public void initRecord(String recordingId, String goodId, int userId){
        System.out.println("初始化记录数据");
        Recording recording = new Recording();
        recording.setId(recordingId);
        recording.setGmtCreate(LocalDateTime.of(2020,1,1,12,30));
        recording.setGmtModifiled(LocalDateTime.now());
        recording.setBuyer(userId);
        recording.setGood(goodId);
        recording.setNum(10);
        recording.setAmount(new BigDecimal(100));
        recording.setOver(false);
        recordingRepository.save(recording);
    }
}
