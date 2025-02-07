package com.miniproject.sustainability.repository;

import com.miniproject.sustainability.entity.WaterSupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaterSupplyRepository extends JpaRepository<WaterSupplyEntity, Long> {

    List<WaterSupplyEntity> findByCityName(String cityName);
}
