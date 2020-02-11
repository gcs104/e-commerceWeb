package com.ecommerce.web.service.implement;

import com.ecommerce.web.entity.User;
import com.ecommerce.web.exception.InsufficientBalanceException;
import com.ecommerce.web.repository.UserRepository;
import com.ecommerce.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository ;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User search(String id) {
        if(userRepository==null){
            System.out.println("fuck!!!");
        }
        return  userRepository.findById(id).orElse(null);
    }

    @Override
    public User create(String name, String password, String gender, String payCode) {
        if(userRepository == null){
            System.out.println("未成功注入");
            return new User();
        }
        String id = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(id);

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setGender(gender);
        user.setPayCode(payCode);
        user.setAmount(new BigDecimal(0));
        user.setGmtCreated(LocalDateTime.now());
        user.setGmtModifiled(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User updateName(String id, String name) {
        User user = search(id);
        user.setName(name);
        user.setGmtModifiled(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User updatePassword(String id, String password) {
        User user = search(id);
        user.setPassword(password);
        user.setGmtModifiled(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User updatePayCode(String id, String payCode) {
        User user = search(id);
        user.setPayCode(payCode);
        user.setGmtModifiled(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User updateProfile(String id, String profile) {
        User user = search(id);
        user.setProfile(profile);
        user.setGmtModifiled(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User updateAddress(String id, String address) {
        User user = search(id);
        user.setAddress(address);
        user.setGmtModifiled(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    //内部函数
    public User updateGoodList(String id, String goodId) {
        User user = search(id);
        String nowGoodId = user.getGoodList();
        if(goodId.equals("")){
            user.setGoodList(goodId);
        }else{
            user.setGoodList(goodId + ";" + nowGoodId);
        }
        return userRepository.save(user);
    }

    @Override
    public User updateAmount(String id, BigDecimal changeAmount, boolean isAdd) throws InsufficientBalanceException {
//        System.out.println("最下面的id为："+id);
        User user = search(id);

        if(user == null){
            System.out.println("发生了什么");
            return null;
        }
        BigDecimal amount;
        if(isAdd == true){
            amount = user.getAmount().add(changeAmount);
        }else{
            amount = user.getAmount().subtract(changeAmount);
            System.out.println("最后的钱为"+amount.toString());
            if(amount.compareTo(new BigDecimal(0)) == -1){
                throw new InsufficientBalanceException();
            }
        }

        user.setAmount(amount);
        user.setGmtModifiled(LocalDateTime.now());
        return userRepository.save(user);
    }
}
