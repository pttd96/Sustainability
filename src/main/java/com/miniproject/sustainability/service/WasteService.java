package com.miniproject.sustainability.service;

import com.miniproject.sustainability.entity.WasteEntity;
import com.miniproject.sustainability.model.Waste;
import com.miniproject.sustainability.repository.WasteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WasteService {

    private final WasteRepository wasteRepository;

    public WasteService(WasteRepository wasteRepository) {
        this.wasteRepository = wasteRepository;
    }

    public List<Waste> getWaste(String cityName) {
        List<WasteEntity> wasteEntities =
                wasteRepository.findByCityName(cityName);
        if (wasteEntities != null) {
            return wasteEntities.stream().map(this::toContract).toList();
        }
        return null;
    }

    private Waste toContract(WasteEntity wasteEntity) {
        Waste waste = new Waste();
        waste.setId(wasteEntity.getId());
        waste.setCityName(wasteEntity.getCityName());
        waste.setWasteType(wasteEntity.getWasteType());
        waste.setWasteVolume(BigDecimal.valueOf(wasteEntity.getWasteVolume()));
        waste.setCollectionDate(wasteEntity.getCollectionDate());
        return waste;
    }
}
