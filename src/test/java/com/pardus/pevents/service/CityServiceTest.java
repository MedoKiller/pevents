package com.pardus.pevents.service;

import com.pardus.pevents.exception.CityNotFoundException;
import com.pardus.pevents.model.City;
import com.pardus.pevents.repo.CityRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    @Mock
    private CityRepo cityRepo;

    @InjectMocks
    private CityService cityService;

    @Test
    void testFindCityById() {
        // arrange
        City testCity = new City();
        testCity.setName("Test name");

        when(cityRepo.findCityById(1L)).thenReturn(Optional.of(testCity));

        // act
        City foundCity = cityService.findCityById(1L);

        // assert
        assertThat(foundCity).isNotNull();
        assertThat(foundCity.getName()).isEqualTo("Test name");
    }

    @Test
    void testFindCityById_ThrowsCityNotFoundException() {
        // arrange
        when(cityRepo.findCityById(1L)).thenReturn(Optional.empty());

        // act & assert
        assertThatThrownBy(() -> cityService.findCityById(1L))
                .isInstanceOf(CityNotFoundException.class)
                .hasMessageContaining("City with id 1 was not found");
    }

    @Test
    void testFindAllCities() {
        // arrange
        List<City> testCityList = new ArrayList<>();

        when(cityRepo.findAll()).thenReturn(testCityList);

        // act
        List<City> foundCities = cityService.findAllCities();

        // assert
        assertThat(foundCities).isNotNull();
    }

    @Test
    void testFindCitiesByMunicipalitiesIds() {
        // arrange
        List<City> testCityList = new ArrayList<>();

        when(cityRepo.findCitiesByMunicipalitiesIds(List.of(1L, 2L, 3L))).thenReturn(testCityList);

        // act
        List<City> foundCities = cityService.findCitiesByMunicipalitiesIds(List.of(1L, 2L, 3L));

        // assert
        assertThat(foundCities).isNotNull();
    }
}