package com.miniproject.sustainability.entity;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import java.time.LocalDate;

@Entity
@Table(name = "electricity")
public class ElectricityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "usage_date", nullable = false)
    private LocalDate usageDate;

    @Column(name = "consumption_kwh", nullable = false)
    private Double consumptionKwh;

    public ElectricityEntity(Long id, String cityName, LocalDate usageDate, Double consumptionKwh) {
        this.id = id;
        this.cityName = cityName;
        this.usageDate = usageDate;
        this.consumptionKwh = consumptionKwh;
    }

    public ElectricityEntity() {

    }

    public Long getId() {
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
