package com.miniproject.sustainability.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "waste")
public class WasteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "collection_date", nullable = false)
    private LocalDate collectionDate;

    @Column(name = "waste_volume", nullable = false)
    private Double wasteVolume;

    @Column(name = "waste_type", nullable = false)
    private String wasteType;
}
