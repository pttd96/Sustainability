package com.miniproject.sustainability.scheduler;

import com.miniproject.sustainability.service.WaterSupplyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WaterSupplyDataScheduler {

    private final WaterSupplyService waterSupplyService;
    private final long fixedRate;

    public WaterSupplyDataScheduler(
            WaterSupplyService waterSupplyService,
            @Value("${scheduler.water-supply.fixed-rate}") long fixedRate) {
        this.waterSupplyService = waterSupplyService;
        this.fixedRate = fixedRate;
    }

    @Scheduled(fixedRateString = "${scheduler.water-supply.fixed-rate}")
    public void fetchElectricityData() {
        waterSupplyService.syncWaterSupplyData();
    }
}
