package com.ecommerce.web.repository;

import com.ecommerce.web.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodRepository extends JpaRepository<Good,String> {
}
