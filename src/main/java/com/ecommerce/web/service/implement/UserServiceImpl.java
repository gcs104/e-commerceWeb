package com.ecommerce.web.service.implement;

import com.ecommerce.web.entity.User;
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
        return userRepository.getOne(id);
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
}
