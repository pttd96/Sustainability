package com.miniproject.sustainability.repository;

import com.miniproject.sustainability.entity.WaterSupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterSupplyRepository extends JpaRepository<WaterSupplyEntity, Long> {
}
