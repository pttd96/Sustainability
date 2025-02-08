package com.miniproject.sustainability.entity;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

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

    public WasteEntity(Long id, String cityName, LocalDate collectionDate, Double wasteVolume, String wasteType) {
        this.cityName = cityName;
        this.collectionDate = collectionDate;
        this.wasteVolume = wasteVolume;
        this.wasteType = wasteType;
    }

    public WasteEntity() {

    }

    public Long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public LocalDate getCollectionDate() {
        return collectionDate;
    }

    public Double getWasteVolume() {
        return wasteVolume;
    }

    public String getWasteType() {
        return wasteType;
    }
}
