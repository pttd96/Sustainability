package com.miniproject.sustainability.entity;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import java.time.LocalDate;

@Entity
@Table(name = "water_supply")
public class WaterSupplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "supply_date", nullable = false)
    private LocalDate supplyDate;

    @Column(name = "water_volume", nullable = false)
    private Double waterVolume;
}
