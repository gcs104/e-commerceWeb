package com.ecommerce.web.service.implement;

import com.ecommerce.web.entity.Good;
import com.ecommerce.web.entity.Recording;
import com.ecommerce.web.entity.User;
import com.ecommerce.web.exception.DoubleOperationException;
import com.ecommerce.web.exception.HistoryCannotUpdateException;
import com.ecommerce.web.exception.NoFindException;
import com.ecommerce.web.repository.GoodRepository;
import com.ecommerce.web.repository.RecordingRepository;
import com.ecommerce.web.repository.UserRepository;
import com.ecommerce.web.service.GoodService;
import com.ecommerce.web.service.RecordingService;
import com.ecommerce.web.service.UserService;
import com.ecommerce.web.util.ToolUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.UUID;

@Service
public class RecordingServiceImpl implements RecordingService {
    RecordingRepository recordingRepository;
    GoodRepository goodRepository;
    UserRepository userRepository;
    UserService userService ;
    GoodService goodService;
    ToolUtil toolUtil;

    @Autowired
    public RecordingServiceImpl(RecordingRepository recordingRepository, GoodRepository goodRepository, UserRepository userRepository, UserService userService, GoodService goodService,ToolUtil toolUtil) {
        this.recordingRepository = recordingRepository;
        this.goodRepository = goodRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.goodService = goodService;
        this.toolUtil = toolUtil;
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
    public Recording search(String id)throws NoFindException {
        Recording recording = recordingRepository.findById(id).orElse(null);
        if(recording == null){
            throw new NoFindException();
        }
        return recording;
    }

    @Override
    public List<Recording> searchFields(String fields) throws NoFindException {
        List<Recording> recordings = recordingRepository.findByFieldsInName(fields);
        if(recordings == null || recordings.isEmpty()){
            throw new NoFindException();
        }
        return recordings;
    }

    @Override
    public Recording create(int buyer, String goodId, int num) throws NoFindException {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        Recording recording = new Recording();
        Good good = goodRepository.findById(goodId).orElse(null);
        User user = userRepository.findById(buyer).orElse(null);

        if(good == null || user == null){
            throw new NoFindException();
        }

        recording.setId(uuid);
        recording.setBuyer(buyer);
        recording.setGood(goodId);
        recording.setGoodName(good.getName());
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
    @Transactional
    public Recording buy(String recordingId) throws Exception {
        Recording recording = search(recordingId);
        User user = userService.search(recording.getBuyer());
        if(recording.isOver()){
            throw new DoubleOperationException();
        }

        String[] strings= user.getShopping().split(";");
        List<String> list = new ArrayList<>();
        for(String s:strings){
            if(!s.equals(recordingId)){
                list.add(s);
            }
        }
        user.setShopping(String.join(";",list));
        if(user.getRecording() == null|| user.getRecording().equals("") ){
            user.setRecording(recordingId);
        }else{
            user.setRecording(recordingId + ";" + user.getRecording());
        }
        userRepository.save(user);
        System.out.println("服务层的amount为："+recording.getAmount().toString());
        userService.updateAmount(recording.getBuyer(),recording.getAmount(),false);
        recording.setOver(true);
        recording.setGmtModifiled(LocalDateTime.now());
        return recordingRepository.save(recording);
    }

    @Override
    @Transactional
    public Recording buy(int buyId, String goodId, int num) throws Exception{
        Recording recording = new Recording();
        Good good = goodRepository.findById(goodId).orElse(null);
        User user = userRepository.findById(buyId).orElse(null);
        if(good == null || user == null){
            throw new NoFindException();
        }
        BigDecimal changeAmount = good.getPrice().multiply(new BigDecimal(num));

        recording.setBuyer(buyId);
        recording.setGood(goodId);
        recording.setGoodName(good.getName());
        recording.setNum(num);
        recording.setOver(true);
        recording.setGmtCreate(LocalDateTime.now());
        recording.setAmount(changeAmount);
        recording.setGmtModifiled(LocalDateTime.now());
        Recording saveRecording = recordingRepository.save(recording);

        if(user.getRecording() == null || user.getRecording().equals("")){
            user.setRecording(recording.getId());
        }else{
            user.setRecording(recording.getId() + ";" + user.getRecording());
        }
        userRepository.save(user);
        userService.updateAmount(buyId,changeAmount,false);

        return saveRecording;
    }

    @Override
    @Transactional
    public void delete(String id) throws Exception {
        Recording recording = search(id);
        if(recording.isOver()){
            throw new NoFindException();
        }
        User user = userService.search(recording.getBuyer());
        List<String> shoppingList = new ArrayList<>();
        for(String s:user.getShopping().split(";")){
            if(!s.equals(id)){
                shoppingList.add(s);
            }
        }
        user.setShopping(String.join(";",shoppingList));

        recordingRepository.delete(recording);

    }

    @Override
    @Transactional
    public Recording update(String id, int num) throws Exception {
        Recording recording = search(id);
        if(recording.isOver()){
            throw new HistoryCannotUpdateException();
        }
        Good good = goodService.search(recording.getGood());
        User user = userService.search(recording.getBuyer());

        recording.setNum(num);
        recording.setAmount(good.getPrice().multiply(new BigDecimal(num)));
        recording.setGmtModifiled(LocalDateTime.now());
        user.setShopping(toolUtil.deleteIdFromList(recording.getId(),user.getShopping()));
        user.setGmtModifiled(LocalDateTime.now());
        userRepository.save(user);

        return recordingRepository.save(recording);
    }
    @Override
    public PageInfo getList(String str,int pageNum,int pageSize){
        String[] split =str.split(";");
        List<Recording> list= new ArrayList<>();
        for(String id:split){
            Recording recording=recordingRepository.findRecordingById(id);
            list.add(recording);
        }
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(list);
        }

}