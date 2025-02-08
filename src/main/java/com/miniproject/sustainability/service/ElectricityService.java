package com.miniproject.sustainability.service;

import com.miniproject.sustainability.entity.ElectricityEntity;
import com.miniproject.sustainability.model.Electricity;
import com.miniproject.sustainability.repository.ElectricityRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ElectricityService {

    private final ElectricityRepository electricityRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust format if needed

    public ElectricityService(ElectricityRepository electricityRepository) {
        this.electricityRepository = electricityRepository;
    }

    public List<Electricity> getElectricityUsage(String cityName) {
        List<ElectricityEntity> electricityEntities =
                electricityRepository.findByCityName(cityName);
        if (electricityEntities != null) {
            return electricityEntities.stream().map(this::toContract).toList();
        }
        return null;
    }

    public List<Electricity> createElectricityUsagesByCSV(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim())) {

            List<ElectricityEntity> electricityEntities = new ArrayList<>();
            for (CSVRecord record : csvParser) {
                LocalDate usageDate = LocalDate.parse(record.get("usage_date"), formatter);
                electricityEntities.add(
                        new ElectricityEntity(
                                record.get("city_name"),
                                usageDate,
                                Double.parseDouble(record.get("electricity_kwh"))
                        )
                );
            }

            electricityRepository.saveAllAndFlush(electricityEntities);

            return electricityEntities.stream().map(this::toContract).toList();

        } catch (Exception e) {
            // logging
        }
        return new ArrayList<>();
    }

    private Electricity toContract(ElectricityEntity electricityEntity) {
        Electricity electricity = new Electricity();
        electricity.setId(electricityEntity.getId());
        electricity.setCityName(electricityEntity.getCityName());
        electricity.setConsumptionKwh(BigDecimal.valueOf(electricityEntity.getConsumptionKwh()));
        electricity.setUsageDate(electricityEntity.getUsageDate());
        return electricity;
    }
}
