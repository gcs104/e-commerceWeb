package com.ecommerce.web.controller;

import com.ecommerce.web.entity.Recording;
import com.ecommerce.web.entity.User;
import com.ecommerce.web.exception.NoFindException;
import com.ecommerce.web.service.RecordingService;
import com.ecommerce.web.service.UserService;
import com.ecommerce.web.util.ToolUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class RecordingController {
    RecordingService recordingService;
    ToolUtil displayUtil;
    UserService userService;
    @Autowired
    public RecordingController(RecordingService recordingService, ToolUtil displayUtil,UserService userService) {
        this.recordingService = recordingService;
        this.displayUtil = displayUtil;
        this.userService=userService;
    }

    @PostMapping(value = "/recording")
    public Recording create(@RequestParam("buyer") int buyer, @RequestParam("good") String good, @RequestParam("num")int num){
        buyer = displayUtil.unpack(buyer);
        Recording recording = null;
        try{
            recording = recordingService.create(buyer,good,num);
            return displayUtil.pack(recording);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/recording")
    public Recording search(@RequestParam("id") String id){
        try{
            return displayUtil.pack(recordingService.search(id));
        }catch(Exception e){e.printStackTrace();}
        return  null;
        //TODO 引导至错误页面
    }

    @GetMapping(value = "/buy")
    public Recording buy(@RequestParam("isShopping")boolean isShopping, @RequestParam("recordingId") String recordingId,
                         @RequestParam("buyId") int buyId, @RequestParam("goodId")String goodId,
                         @RequestParam("num")String num){
        try{
            if(isShopping){
                System.out.println("?213:"+recordingId);
                return displayUtil.pack(recordingService.buy(recordingId));
            }else{
                return displayUtil.pack(recordingService.buy(buyId,goodId,Integer.parseInt(num)));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/recording/delete")
    public void delete(@RequestParam("recordingId") String recordingId){
        try{
            recordingService.delete(recordingId);
        }catch (Exception e){e.printStackTrace();}
    }

    @PostMapping(value = "/recording/update")
    public Recording update(@RequestParam("recordingId") String id, @RequestParam("num")int num){
        try{
            return displayUtil.pack(recordingService.update(id,num));
        }catch (Exception e){e.printStackTrace();}
        return null;
    }
    @PostMapping(value ="/recording/shoppinglist")//购物车
    public PageInfo list ( @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "5")int pageSize,
                           @RequestParam("userId")int id){
        try{
            User user=userService.search(displayUtil.unpack(id));
            String shopping=user.getShopping();
           return recordingService.getList(shopping,pageNum,pageSize);
        } catch (NoFindException e) {
            e.printStackTrace();
        }
         return null;
    }
    @PostMapping(value ="/recording/historylist")//历史交易
    public PageInfo historylist ( @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "5")int pageSize,
                           @RequestParam("userId")int id){
        try{
            User user=userService.search(id);
            String recording=user.getRecording();
            return recordingService.getList(recording,pageNum,pageSize);
        } catch (NoFindException e) {
            e.printStackTrace();
        }
        return null;
    }
}
