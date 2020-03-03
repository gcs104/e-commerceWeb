package com.ecommerce.web.repository;

import com.ecommerce.web.entity.Recording;
import com.ecommerce.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "select * from recording where name like %:fields% ",nativeQuery = true)
    List<User> findByFieldsInName(String fields);
}
