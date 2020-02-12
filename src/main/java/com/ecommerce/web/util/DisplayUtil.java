package com.ecommerce.web.util;

import com.ecommerce.web.entity.Good;
import com.ecommerce.web.entity.Recording;
import com.ecommerce.web.entity.User;
import com.ecommerce.web.model.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//传到前台去前的包装和到后台的拆包装
@Component
public class DisplayUtil {
    private Config CONFIG;
    @Autowired
    public DisplayUtil(Config CONFIG) {
        this.CONFIG = CONFIG;
    }

    public User pack(User user){
        user.setId(user.getId() + CONFIG.getUserIdAdd());
        return user;
    }

    public Recording pack(Recording recording){
        recording.setBuyer(recording.getBuyer() + CONFIG.getUserIdAdd());
        return recording;
    }
    public Good pack(Good good){
        good.setMasterId(good.getMasterId() + CONFIG.getUserIdAdd());
        return good;
    }

    public int unpack(int userId){
        userId -= CONFIG.getUserIdAdd();
        return userId;
    }
}
