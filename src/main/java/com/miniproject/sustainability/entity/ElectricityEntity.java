package com.miniproject.sustainability.entity;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "electricity")
public class ElectricityEntity {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "usage_date", nullable = false)
    private LocalDate usageDate;

    @Column(name = "consumption_kwh", nullable = false)
    private Double consumptionKwh;

    public ElectricityEntity(String cityName, LocalDate usageDate, Double consumptionKwh) {
        this.cityName = cityName;
        this.usageDate = usageDate;
        this.consumptionKwh = consumptionKwh;
    }

    public ElectricityEntity() {

    }

    public String getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public LocalDate getUsageDate() {
        return usageDate;
    }

    public Double getConsumptionKwh() {
        return consumptionKwh;
    }
}
