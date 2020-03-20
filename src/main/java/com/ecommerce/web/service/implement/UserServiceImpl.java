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
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository ;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User search(int id) throws NoFindException{
        if(userRepository==null){
            System.out.println("fuck!!!");
        }
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            throw new NoFindException();
        }
        return user;
    }
    @Override
    public boolean login(int id,String password) throws NoFindException {
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            throw new NoFindException();
        }
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
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

//    @Override
//    public User updateName(int id, String name) throws NoFindException{
//        User user = search(id);
//        user.setName(name);
//        user.setGmtModifiled(LocalDateTime.now());
//        return userRepository.save(user);
//    }

    @Override
    public User updatePassword(int id, String password) throws NoFindException{
        id = id;
        User user = search(id);
        user.setPassword(password);
        user.setGmtModifiled(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User updatePayCode(int id, String payCode) throws NoFindException{
        User user = search(id);
        user.setPayCode(payCode);
        user.setGmtModifiled(LocalDateTime.now());
        return userRepository.save(user);
    }

//    @Override
//    public User updateProfile(int id, String profile)throws NoFindException {
//        User user = search(id);
//        user.setProfile(profile);
//        user.setGmtModifiled(LocalDateTime.now());
//        return userRepository.save(user);
//    }
//
//    @Override
//    public User updateAddress(int id, String address, boolean isAdd) throws Exception {
//        User user = search(id);
//        String oldAddress = user.getAddress();
//        if (isAdd) {
//            if (oldAddress == null || oldAddress.equals("")) {
//                user.setAddress(address);
//            } else {
//                user.setAddress(address + ";" + oldAddress);
//            }
//            user.setGmtModifiled(LocalDateTime.now());
//            return userRepository.save(user);
//        } else {
//            if (oldAddress == null || oldAddress.equals("")) {
//                throw new NoFindException();
//            }else{
//                //TODO
//            }
//        }
//        return null;
//    }



    @Override
    //内部函数
    public User updateGoodList(int id, String goodId)throws Exception {
        User user = search(id);
        String nowGoodId = user.getGoodList();
        if(nowGoodId == null||nowGoodId.equals("")){
            user.setGoodList(goodId);
        }else{
            user.setGoodList(goodId + ";" + nowGoodId);
        }
        user.setGmtModifiled(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public void updateAmount(int id, BigDecimal changeAmount, boolean isAdd) throws Exception {
//        System.out.println("最下面的id为："+id);
        User user = search(id);

        if(user == null){
            System.out.println("发生了什么");
            return ;
        }
        BigDecimal amount;
        if(isAdd){
            amount = user.getAmount().add(changeAmount);
        }else{
            amount = user.getAmount().subtract(changeAmount);
            System.out.println("最后的钱为"+amount.toString());
            if(amount.compareTo(new BigDecimal(0)) < 0){
                throw new InsufficientBalanceException();
            }
        }

        user.setAmount(amount);
        user.setGmtModifiled(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public User update(int id, String name, String profile, String address) throws Exception {
        User user = search(id);
        user.setName(name);
        user.setAddress(address);
        user.setProfile(profile);
        user.setGmtModifiled(LocalDateTime.now());
        return userRepository.save(user);
    }
}
