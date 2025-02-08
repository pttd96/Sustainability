package com.miniproject.sustainability.scheduler;

import com.miniproject.sustainability.service.WaterSupplyService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WaterSupplyDataScheduler {

    private final WaterSupplyService waterSupplyService;

    public WaterSupplyDataScheduler(WaterSupplyService waterSupplyService) {
        this.waterSupplyService = waterSupplyService;
    }

    @Scheduled(fixedRate = 60000) // every 10 mins
    public void fetchElectricityData() {
        waterSupplyService.syncWaterSupplyData();
    }
}
