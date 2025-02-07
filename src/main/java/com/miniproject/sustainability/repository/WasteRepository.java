package com.miniproject.sustainability.repository;

import com.miniproject.sustainability.entity.WasteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WasteRepository extends JpaRepository<WasteEntity, Long> {

    List<WasteEntity> findByCityName(String cityName);
}
