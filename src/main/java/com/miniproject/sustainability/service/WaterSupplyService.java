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
    private final WaterSupplyExternalService waterSupplyExternalService;

    public WaterSupplyService(
            WaterSupplyRepository waterSupplyRepository,
            WaterSupplyExternalService waterSupplyExternalService
    ) {
        this.waterSupplyRepository = waterSupplyRepository;
        this.waterSupplyExternalService = waterSupplyExternalService;
    }

    public List<WaterSupply> getWaterSupply(String cityName) {
        List<WaterSupplyEntity> waterSupplyEntities =
                waterSupplyRepository.findByCityName(cityName);
        if (waterSupplyEntities != null) {
            return waterSupplyEntities.stream().map(this::toContract).toList();
        }
        return null;
    }

    public WaterSupply syncWaterSupplyData() {
        WaterSupply data = waterSupplyExternalService.getWaterSupplyDataFromExternal();
        WaterSupplyEntity waterSupplyEntity = new WaterSupplyEntity(
                data.getCityName(),
                data.getSupplyDate(),
                data.getWaterVolume().doubleValue()
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
