package com.ecommerce.web.repository;

import com.ecommerce.web.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodRepository extends JpaRepository<Good,String> {
    @Query(value = "select * from good where name like %:fields% ",nativeQuery = true)
    List<Good> findByFieldsInName(String fields);
}
