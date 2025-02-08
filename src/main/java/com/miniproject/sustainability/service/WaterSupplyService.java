package com.miniproject.sustainability.service;

import com.miniproject.sustainability.entity.WaterSupplyEntity;
import com.miniproject.sustainability.model.WaterSupply;
import com.miniproject.sustainability.repository.WaterSupplyRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WaterSupplyService {

    private final WaterSupplyRepository waterSupplyRepository;

    public WaterSupplyService(WaterSupplyRepository waterSupplyRepository) {
        this.waterSupplyRepository = waterSupplyRepository;
    }

    public List<WaterSupply> getWaterSupply(String cityName) {
        List<WaterSupplyEntity> waterSupplyEntities =
                waterSupplyRepository.findByCityName(cityName);
        if (waterSupplyEntities != null) {
            return waterSupplyEntities.stream().map(this::toContract).toList();
        }
        return null;
    }

    public WaterSupply createWaterSupply(WaterSupply waterSupply) {
        WaterSupplyEntity waterSupplyEntity = new WaterSupplyEntity(
                waterSupply.getCityName(),
                waterSupply.getSupplyDate(),
                waterSupply.getWaterVolume().doubleValue()
        );
        return toContract(waterSupplyRepository.saveAndFlush(waterSupplyEntity));
    }

    private WaterSupply toContract(WaterSupplyEntity waterSupplyEntity) {
        WaterSupply waterSupply = new WaterSupply();
        waterSupply.setId(waterSupplyEntity.getId());
        waterSupply.setCityName(waterSupplyEntity.getCityName());
        waterSupply.setSupplyDate(waterSupplyEntity.getSupplyDate());
        waterSupply.setWaterVolume(BigDecimal.valueOf(waterSupplyEntity.getWaterVolume()));
        return waterSupply;
    }
}
