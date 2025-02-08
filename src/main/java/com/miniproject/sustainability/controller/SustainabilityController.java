package com.miniproject.sustainability.controller;

import com.miniproject.sustainability.api.SustainabilityApi;
import com.miniproject.sustainability.model.Electricity;
import com.miniproject.sustainability.model.Waste;
import com.miniproject.sustainability.model.WaterSupply;
import com.miniproject.sustainability.service.ElectricityService;
import com.miniproject.sustainability.service.WasteService;
import com.miniproject.sustainability.service.WaterSupplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class SustainabilityController implements SustainabilityApi {
    private final WaterSupplyService waterSupplyService;
    private final WasteService wasteService;
    private final ElectricityService electricityService;

    public SustainabilityController(
            WaterSupplyService waterSupplyService,
            WasteService wasteService,
            ElectricityService electricityService
    ) {
        this.waterSupplyService = waterSupplyService;
        this.wasteService = wasteService;
        this.electricityService = electricityService;
    }

    @Override
    public ResponseEntity<List<WaterSupply>> getWaterSupplyByCity(String cityName) {
        List<WaterSupply> result = waterSupplyService.getWaterSupply(cityName);
        return ResponseEntity.ok(Objects.requireNonNullElseGet(result, ArrayList::new));
    }

    @Override
    public ResponseEntity<List<Waste>> getWasteByCity(String cityName) {
        List<Waste> result = wasteService.getWaste(cityName);
        return ResponseEntity.ok(Objects.requireNonNullElseGet(result, ArrayList::new));
    }

    @Override
    public ResponseEntity<List<Electricity>> getElectricityByCity(String cityName) {
        List<Electricity> result = electricityService.getElectricityUsage(cityName);
        return ResponseEntity.ok(Objects.requireNonNullElseGet(result, ArrayList::new));
    }

    @Override
    public ResponseEntity<Waste> createWasteRecord(Waste waste) {
        Waste result = wasteService.createWasteRecord(waste);
        if (Objects.nonNull(result)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        return ResponseEntity.internalServerError().build();
    }

    @Override
    public ResponseEntity<WaterSupply> createWaterSupply(WaterSupply waterSupply) {
        WaterSupply result = waterSupplyService.createWaterSupply(waterSupply);
        if (Objects.nonNull(result)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        return ResponseEntity.internalServerError().build();
    }

    @Override
    public ResponseEntity<List<Electricity>> uploadElectricityUsage(MultipartFile file) {
        List<Electricity> result = electricityService.createElectricityUsagesByCSV(file);
        if (result.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(result);
    }
}
