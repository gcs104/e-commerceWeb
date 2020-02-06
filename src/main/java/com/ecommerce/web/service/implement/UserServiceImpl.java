package com.ecommerce.web.service.implement;

import com.ecommerce.web.entity.User;
import com.ecommerce.web.exception.InsufficientBalanceException;
import com.ecommerce.web.repository.UserRepository;
import com.ecommerce.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository ;
    @Autowired
    void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User search(int id) {
        return userRepository.findById(id).orElse(null);
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
    public User updateAddress(int id, String address) {
        User user = search(id);
        user.setAddress(address);
        user.setGmtModifiled(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User updateAmount(int id, BigDecimal changeAmount) throws InsufficientBalanceException {
        User user = search(id);
        BigDecimal amount = user.getAmount().add(changeAmount);
        if(amount.compareTo(new BigDecimal(0)) == -1){
            throw new InsufficientBalanceException();
        }

        user.setAmount(amount);
        user.setGmtModifiled(LocalDateTime.now());
        return userRepository.save(user);
    }
}
