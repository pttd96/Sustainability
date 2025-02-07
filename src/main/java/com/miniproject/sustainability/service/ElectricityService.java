package com.miniproject.sustainability.service;

import com.miniproject.sustainability.entity.ElectricityEntity;
import com.miniproject.sustainability.model.Electricity;
import com.miniproject.sustainability.repository.ElectricityRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ElectricityService {

    private final ElectricityRepository electricityRepository;

    public ElectricityService(ElectricityRepository electricityRepository) {
        this.electricityRepository = electricityRepository;
    }

    public List<Electricity> getElectricityUsage(String cityName) {
        List<ElectricityEntity> electricityEntities =
                electricityRepository.findByCityName(cityName);
        if (electricityEntities != null) {
            return electricityEntities.stream().map(this::toContract).toList();
        }
        return null;
    }

    private Electricity toContract(ElectricityEntity electricityEntity) {
        Electricity electricity = new Electricity();
        electricity.setId(electricityEntity.getId());
        electricity.setCityName(electricityEntity.getCityName());
        electricity.setConsumptionKwh(BigDecimal.valueOf(electricityEntity.getConsumptionKwh()));
        electricity.setUsageDate(electricityEntity.getUsageDate());
        return electricity;
    }
}
