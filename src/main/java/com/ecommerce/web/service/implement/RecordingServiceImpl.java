package com.ecommerce.web.service.implement;

import com.ecommerce.web.entity.Good;
import com.ecommerce.web.entity.Recording;
import com.ecommerce.web.repository.GoodRepository;
import com.ecommerce.web.repository.RecordingRepository;
import com.ecommerce.web.service.RecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class RecordingServiceImpl implements RecordingService {
    RecordingRepository recordingRepository;
    GoodRepository goodRepository;
    @Autowired
    void setRecordingRepository(RecordingRepository recordingRepository){
        this.recordingRepository = recordingRepository;
    }
    @Autowired
    void setGoodRepository(GoodRepository goodRepository){
        this.goodRepository = goodRepository;
    }

    @Override
    public Recording search(int id) {
        return recordingRepository.getOne(id);
    }

    @Override
    public Recording create(int buyer, int goodId, int num) {
        Recording recording = new Recording();
        Good good = goodRepository.getOne(goodId);
        recording.setBuyer(buyer);
        recording.setGood(goodId);
        recording.setNum(num);
        recording.setOver(false);
        recording.setGmtCreate(LocalDateTime.now());
        recording.setAmount(good.getPrice().multiply(new BigDecimal(num)));
        recording.setGmtModifiled(LocalDateTime.now());
        return recordingRepository.save(recording);
    }
}

