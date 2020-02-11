package com.ecommerce.web.service.implement;

import com.ecommerce.web.entity.User;
import com.ecommerce.web.exception.InsufficientBalanceException;
import com.ecommerce.web.exception.NoFindException;
import com.ecommerce.web.repository.UserRepository;
import com.ecommerce.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository ;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User search(int id) {
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
        User user = new User();
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
    public User updateName(int id, String name) {
        User user = search(id);
        user.setName(name);
        user.setGmtModifiled(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User updatePassword(int id, String password) {
        User user = search(id);
        user.setPassword(password);
        user.setGmtModifiled(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User updatePayCode(int id, String payCode) {
        User user = search(id);
        user.setPayCode(payCode);
        user.setGmtModifiled(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User updateProfile(int id, String profile) {
        User user = search(id);
        user.setProfile(profile);
        user.setGmtModifiled(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User updateAddress(int id, String address, boolean isAdd) throws Exception {
        User user = search(id);
        String oldAddress = user.getAddress();
        if (isAdd) {
            if (oldAddress == null || oldAddress.equals("")) {
                user.setAddress(address);
            } else {
                user.setAddress(address + ";" + oldAddress);
            }
            user.setGmtModifiled(LocalDateTime.now());
            return userRepository.save(user);
        } else {
            if (oldAddress == null || oldAddress.equals("")) {
                throw new NoFindException();
            }else{
                //TODO
            }
        }
        return null;
    }


    @Override
    //内部函数
    public User updateGoodList(int id, String goodId) {
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
    public User updateAmount(int id, BigDecimal changeAmount, boolean isAdd) throws InsufficientBalanceException {
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
