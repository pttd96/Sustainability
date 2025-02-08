package com.miniproject.sustainability.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "water_supply")
public class WaterSupplyEntity {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "supply_date", nullable = false)
    private LocalDate supplyDate;

    @Column(name = "water_volume", nullable = false)
    private Double waterVolume;

    public WaterSupplyEntity(String cityName, LocalDate supplyDate, Double waterVolume) {
        this.cityName = cityName;
        this.supplyDate = supplyDate;
        this.waterVolume = waterVolume;
    }

    public WaterSupplyEntity() {

    }

    public String getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public LocalDate getSupplyDate() {
        return supplyDate;
    }

    public Double getWaterVolume() {
        return waterVolume;
    }
}
