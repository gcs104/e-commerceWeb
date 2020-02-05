package com.ecommerce.web.repository;

import com.ecommerce.web.entity.Recording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordingRepository extends JpaRepository<Recording,Integer> {
}
