package com.miniproject.sustainability.service;

import com.miniproject.sustainability.entity.WasteEntity;
import com.miniproject.sustainability.model.Waste;
import com.miniproject.sustainability.model.WaterSupply;
import com.miniproject.sustainability.repository.WasteRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WasteServiceTest {

    private final WasteRepository wasteRepository = mock();

    private final WasteService wasteService = new WasteService(wasteRepository);

    @Test
    public void testGetWasteByCity() {
        // given
        String cityName = "HoChiMinh";
        when(wasteRepository.findByCityName(cityName)).thenReturn(mockWasteEntities());

        // when
        List<Waste> result = wasteService.getWaste(cityName);

        // then
        verify(wasteRepository).findByCityName(cityName);
        Set<String> cities = result.stream().map(Waste::getCityName).collect(Collectors.toSet());
        assertEquals(1, cities.size());
        assertEquals(cityName, cities.stream().findAny().orElse(""));
    }

    @Test
    public void testCreateWaste() {
        // given
        Waste waste = new Waste();
        waste.setCityName("HoChiMinh");
        waste.setWasteVolume(BigDecimal.valueOf(50));
        waste.setWasteType("plastic");
        waste.setCollectionDate(LocalDate.now());

        when(wasteRepository.saveAndFlush(any())).thenReturn(
                new WasteEntity(
                        waste.getCityName(),
                        waste.getCollectionDate(),
                        waste.getWasteVolume().doubleValue(),
                        waste.getWasteType()
                )
        );

        // when
        Waste result = wasteService.createWasteRecord(waste);

        // then
        verify(wasteRepository).saveAndFlush(any());
        assertEquals(waste.getWasteType(), result.getWasteType());
        assertEquals(waste.getCollectionDate(), result.getCollectionDate());
        assertEquals(waste.getCityName(), result.getCityName());
    }

    private List<WasteEntity> mockWasteEntities() {
        return List.of(
                new WasteEntity("HoChiMinh", LocalDate.now(), 100.0, "plastic"),
                new WasteEntity("HoChiMinh", LocalDate.now().minusDays(1), 99.0, "organic")
        );
    }
}
