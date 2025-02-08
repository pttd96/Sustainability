package com.miniproject.sustainability.service;

import com.miniproject.sustainability.model.WaterSupply;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class WaterSupplyExternalService {

    public WaterSupply getWaterSupplyDataFromExternal() {
        // API call to somewhere
        // just pseudo imagination
        // might need a different DTO but reusing WaterSupply to save effort
        WaterSupply dataFromExternal = new WaterSupply();
        dataFromExternal.setWaterVolume(BigDecimal.ZERO);
        dataFromExternal.setSupplyDate(LocalDate.now());
        dataFromExternal.setCityName("HoChiMinh");
        return dataFromExternal;
    }
}
