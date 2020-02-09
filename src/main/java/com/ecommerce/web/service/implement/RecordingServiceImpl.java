package com.ecommerce.web.service.implement;

import com.ecommerce.web.entity.Good;
import com.ecommerce.web.entity.Recording;
import com.ecommerce.web.entity.User;
import com.ecommerce.web.exception.DoubleOperationException;
import com.ecommerce.web.exception.InsufficientBalanceException;
import com.ecommerce.web.exception.NoFindException;
import com.ecommerce.web.repository.GoodRepository;
import com.ecommerce.web.repository.RecordingRepository;
import com.ecommerce.web.repository.UserRepository;
import com.ecommerce.web.service.RecordingService;
import com.ecommerce.web.service.UserService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecordingServiceImpl implements RecordingService {
    RecordingRepository recordingRepository;
    GoodRepository goodRepository;
    UserRepository userRepository;
    UserService userService = new UserServiceImpl();

    @Autowired
    public RecordingServiceImpl(RecordingRepository recordingRepository, GoodRepository goodRepository, UserRepository userRepository, UserService userService) {
        this.recordingRepository = recordingRepository;
        this.goodRepository = goodRepository;
        this.userRepository = userRepository;
    }

//    @Autowired
//    void setRecordingRepository(RecordingRepository recordingRepository){
//        this.recordingRepository = recordingRepository;
//    }
//    @Autowired
//    void setGoodRepository(GoodRepository goodRepository){
//        this.goodRepository = goodRepository;
//    }
//    @Autowired
//    void setUserRepository(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }

    @Override
    public Recording search(int id) {
        return recordingRepository.findById(id).orElse(null);
    }

    @Override
    public Recording create(int buyer, int goodId, int num) throws NoFindException {
        Recording recording = new Recording();
        Good good = goodRepository.findById(goodId).orElse(null);
        User user = userRepository.findById(buyer).orElse(null);

        if(good == null || user == null){
            throw new NoFindException();
        }

        recording.setBuyer(buyer);
        recording.setGood(goodId);
        recording.setNum(num);
        recording.setOver(false);
        recording.setGmtCreate(LocalDateTime.now());
        recording.setAmount(good.getPrice().multiply(new BigDecimal(num)));
        recording.setGmtModifiled(LocalDateTime.now());
        recordingRepository.save(recording);

        if(user.getShopping().equals("")){
            user.setShopping(String.valueOf(recording.getId()));
        }else{
            String userShoppingRecording = recording.getId() + ";" + user.getShopping();
            user.setShopping(userShoppingRecording);
        }
        userRepository.save(user);

        return recording;
    }

    @Override
    //@Transactional
    public Recording buy(int recordingId) throws Exception {
        Recording recording = search(recordingId);
        User user = userRepository.findById(recording.getBuyer()).orElse(null);
        if(user == null){
            throw new NoFindException();
        }if(recording.isOver()){
            throw new DoubleOperationException();
        }

        String[] strings= user.getShopping().split(";");
        List<String> list = new ArrayList<>();
        for(String s:strings){
            if(!s.equals(String.valueOf(recordingId)) ){
                list.add(s);
            }
        }
        user.setShopping(String.join(";",list.toArray(new String[list.size()])));
        if(user.getRecording().equals("")){
            user.setRecording(String.valueOf(recordingId));
        }else{
            user.setRecording(recordingId + ";" + user.getRecording());
        }
        userRepository.save(user);
        System.out.println("服务层的amount为："+recording.getAmount().toString());
        userService.updateAmount(1,recording.getAmount(),false);
        recording.setOver(true);
        return recordingRepository.save(recording);
    }

    @Override
    @Transactional
    public Recording buy(int buyId, int goodId, int num) throws Exception{
        Recording recording = new Recording();
        Good good = goodRepository.findById(goodId).orElse(null);
        User user = userRepository.findById(buyId).orElse(null);
        if(good == null || user == null){
            throw new NoFindException();
        }
        BigDecimal changeAmount = good.getPrice().multiply(new BigDecimal(num));

        recording.setBuyer(buyId);
        recording.setGood(goodId);
        recording.setNum(num);
        recording.setOver(true);
        recording.setGmtCreate(LocalDateTime.now());
        recording.setAmount(changeAmount);
        recording.setGmtModifiled(LocalDateTime.now());
        Recording saveRecording = recordingRepository.save(recording);

        String userRecording = user.getRecording() + ";" + recording.getId();
        user.setShopping(userRecording);
        userRepository.save(user);
        userService.updateAmount(buyId,changeAmount,false);

        return saveRecording;
    }
}