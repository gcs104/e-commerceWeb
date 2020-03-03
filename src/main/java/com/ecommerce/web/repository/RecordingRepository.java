package com.ecommerce.web.repository;

import com.ecommerce.web.entity.Good;
import com.ecommerce.web.entity.Recording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordingRepository extends JpaRepository<Recording,String> {
    @Query(value = "select * from recording where goodName like %:fields% ",nativeQuery = true)
    List<Recording> findByFieldsInName(String fields);
}
