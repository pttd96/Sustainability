package com.miniproject.sustainability.entity;

import jakarta.persistence.*;

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
}
