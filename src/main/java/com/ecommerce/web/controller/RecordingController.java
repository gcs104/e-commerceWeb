package com.ecommerce.web.controller;

import com.ecommerce.web.entity.Recording;
import com.ecommerce.web.service.RecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;

@RestController
public class RecordingController {
    RecordingService recordingService;
    @Autowired
    void setRecordingService(RecordingService recordingService) {
        this.recordingService = recordingService;
    }

    @PostMapping(value = "/recording")
    public Recording create(@RequestParam("buyer") int buyer, @RequestParam("good") String good, @RequestParam("num")int num){
        Recording recording = null;
        try{
            recording = recordingService.create(buyer,good,num);
        }catch(Exception e){
            e.printStackTrace();
        }
        return recording;
    }
    @GetMapping(value = "/recording")
    public Recording search(@RequestParam("id")String id){
        try{
            return recordingService.search(id);
        }catch(Exception e){e.printStackTrace();}
        return  null;
        //TODO 引导至错误页面
    }

    @GetMapping(value = "/buy")
    public Recording buy(@RequestParam("isShopping")boolean isShopping, @RequestParam("recordingId")String recordingId,
                         @RequestParam("buyId") int buyId, @RequestParam("goodId")String goodId,
                         @RequestParam("num")String num){
        try{
            if(isShopping){
                System.out.println("?213:"+recordingId);
                return recordingService.buy(recordingId);
            }else{
                return recordingService.buy(buyId,goodId,Integer.parseInt(num));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/recording/delete")
    public void delete(@RequestParam("recordingId")String recordingId){
        try{
            recordingService.delete(recordingId);
        }catch (Exception e){e.printStackTrace();}
    }

    @PostMapping(value = "/recording/update")
    public Recording update(@RequestParam("recordingId")String id,@RequestParam("num")int num){
        try{
            return recordingService.update(id,num);
        }catch (Exception e){e.printStackTrace();}
        return null;
    }
}
