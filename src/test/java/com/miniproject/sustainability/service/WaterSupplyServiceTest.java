package com.miniproject.sustainability.service;

import com.miniproject.sustainability.entity.WaterSupplyEntity;
import com.miniproject.sustainability.model.WaterSupply;
import com.miniproject.sustainability.repository.WaterSupplyRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WaterSupplyServiceTest {

    private final WaterSupplyRepository waterSupplyRepository = mock();
    private final WaterSupplyExternalService waterSupplyExternalService = mock();

    private final WaterSupplyService waterSupplyService =
            new WaterSupplyService(waterSupplyRepository, waterSupplyExternalService);

    @Test
    public void testGetElectricityUsagesByCity() {
        // given
        String cityName = "HoChiMinh";
        when(waterSupplyRepository.findByCityName(cityName)).thenReturn(mockWaterSupplyEntities());

        // when
        List<WaterSupply> result = waterSupplyService.getWaterSupply(cityName);

        // then
        verify(waterSupplyRepository).findByCityName(cityName);
        Set<String> cities = result.stream().map(WaterSupply::getCityName).collect(Collectors.toSet());
        assertEquals(1, cities.size());
        assertEquals(cityName, cities.stream().findAny().orElse(""));
    }

    private List<WaterSupplyEntity> mockWaterSupplyEntities() {
        return List.of(
                new WaterSupplyEntity("HoChiMinh", LocalDate.now(), 100.0),
                new WaterSupplyEntity("HoChiMinh", LocalDate.now().minusDays(1), 99.0)
        );
    }
}
