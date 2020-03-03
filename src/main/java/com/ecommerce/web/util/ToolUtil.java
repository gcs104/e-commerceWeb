package com.ecommerce.web.util;

import com.ecommerce.web.entity.Good;
import com.ecommerce.web.entity.Recording;
import com.ecommerce.web.entity.User;
import com.ecommerce.web.exception.NoFindException;
import com.ecommerce.web.model.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//传到前台去前的包装和到后台的拆包装
@Component
public class ToolUtil {
    private Config CONFIG;
    @Autowired
    public ToolUtil(Config CONFIG) {
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

    //从列表中删除ID(购物车，商品目录等)
    //返回删除后的字符串
    public String deleteIdFromList(String id,String idList) throws NoFindException{
        List<String> list = new ArrayList<>();
        Boolean isDelete = false;
        for (String s:idList.split(";")){
            if(s.equals(id) ){
                isDelete = true;
                continue;
            }
            list.add(s);
        }
        if(!isDelete){
            throw new NoFindException();
        }
        return String.join(";",list);
    }
}
