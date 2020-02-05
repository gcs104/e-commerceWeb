package com.ecommerce.web.controller;

import com.ecommerce.web.entity.Recording;
import com.ecommerce.web.service.RecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecordingController {
    RecordingService recordingService;
    @Autowired
    void setRecordingService(RecordingService recordingService) {
        this.recordingService = recordingService;
    }

    @PostMapping(value = "/recording")
    public Recording create(@RequestParam("buyer")int buyer,@RequestParam("good")int good,@RequestParam("num")int num){
        return recordingService.create(buyer,good,num);
    }
    @GetMapping(value = "/recording")
    public Recording search(@RequestParam("id")int id){
        return recordingService.search(id);
    }
}
