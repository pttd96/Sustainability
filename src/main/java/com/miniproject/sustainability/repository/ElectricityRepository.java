package com.miniproject.sustainability.repository;

import com.miniproject.sustainability.entity.ElectricityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectricityRepository extends JpaRepository<ElectricityEntity, Long> {

    List<ElectricityEntity> findByCityName(String cityName);

}
