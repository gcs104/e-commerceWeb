package com.ecommerce.web.service.implement;

import com.ecommerce.web.entity.User;
import com.ecommerce.web.repository.UserRepository;
import com.ecommerce.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User search(String id) {
        return userRepository.getOne(id);
    }

    @Override
    public User create(String name, String password, String gender, String payCode) {
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
}
