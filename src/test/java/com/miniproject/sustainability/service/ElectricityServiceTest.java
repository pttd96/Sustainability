package com.miniproject.sustainability.service;

import com.miniproject.sustainability.entity.ElectricityEntity;
import com.miniproject.sustainability.model.Electricity;
import com.miniproject.sustainability.repository.ElectricityRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class ElectricityServiceTest {

    private final ElectricityRepository electricityRepository = mock();

    private final ElectricityService electricityService = new ElectricityService(electricityRepository);

    private static MockMultipartFile mockCsvFile;

    @BeforeAll
    public static void setup() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8),
                CSVFormat.DEFAULT.withHeader("city_name", "usage_date", "electricity_kwh"));

        csvPrinter.printRecord("HoChiMinh", "2024-02-10", "500.0");
        csvPrinter.printRecord("DaLat", "2024-02-11", "600.5");
        csvPrinter.flush();

        mockCsvFile = new MockMultipartFile(
                "file",
                "electricity_usage.csv",
                "text/csv",
                outputStream.toByteArray()
        );
    }

    @Test
    public void testUploadElectricityUsage() {
        // given
        when(electricityRepository.saveAllAndFlush(anyList())).thenReturn(new ArrayList<>());

        // when
        List<Electricity> result = electricityService.createElectricityUsagesByCSV(mockCsvFile);

        // then
        verify(electricityRepository).saveAllAndFlush(anyList());
        Set<String> cities = result.stream().map(Electricity::getCityName).collect(Collectors.toSet());
        assertTrue(cities.containsAll(Set.of("HoChiMinh", "DaLat")));
    }

    @Test
    public void testGetElectricityUsagesByCity() {
        // given
        String cityName = "HoChiMinh";
        when(electricityRepository.findByCityName(cityName)).thenReturn(mockElectricityEntities());

        // when
        List<Electricity> result = electricityService.getElectricityUsage(cityName);

        // then
        verify(electricityRepository).findByCityName(cityName);
        Set<String> cities = result.stream().map(Electricity::getCityName).collect(Collectors.toSet());
        assertEquals(1, cities.size());
        assertEquals(cityName, cities.stream().findAny().orElse(""));
    }

    private List<ElectricityEntity> mockElectricityEntities() {
        return List.of(
                new ElectricityEntity("HoChiMinh", LocalDate.now(), 100.0),
                new ElectricityEntity("HoChiMinh", LocalDate.now().minusDays(1), 99.0)
        );
    }
}
