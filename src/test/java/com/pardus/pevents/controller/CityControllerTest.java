package com.pardus.pevents.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pardus.pevents.dto.CityDTO;
import com.pardus.pevents.mapper.ResponseMapper;
import com.pardus.pevents.model.City;
import com.pardus.pevents.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CityController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    @MockBean
    private ResponseMapper responseMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private City city1;
    private City city2;
    private CityDTO cityDTO1;
    private CityDTO cityDTO2;

    @BeforeEach
    void setUp() {
        this.city1 = new City();
        this.city2 = new City();
        this.city1.setName("City1");
        this.city2.setName("City2");

        this.cityDTO1 = new CityDTO();
        this.cityDTO2 = new CityDTO();
        this.cityDTO1.setName("City1DTO");
        this.cityDTO2.setName("City2DTO");
    }

    @Test
    void testGetAllCities() throws Exception {
        // arrange
        List<City> cities = Arrays.asList(city1, city2);

        List<CityDTO> citiesDTOs = Arrays.asList(cityDTO1, cityDTO2);

        when(cityService.findAllCities()).thenReturn(cities);
        when(responseMapper.mapCities(cities)).thenReturn(citiesDTOs);

        // act
        ResultActions resultActions = mockMvc.perform(get("/city/all")
                        .contentType(MediaType.APPLICATION_JSON));

        // assert
        resultActions.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(citiesDTOs)));

        verify(cityService, times(1)).findAllCities();
    }

    @Test
    void testGetMunicipalitiesCities() throws Exception {
        // arrange
        List<Long> municipalitiesIds = Arrays.asList(1L, 2L);
        List<City> cities = Arrays.asList(city1, city2);

        when(cityService.findCitiesByMunicipalitiesIds(municipalitiesIds)).thenReturn(cities);

        ArgumentCaptor<List<Long>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        // act
        ResultActions resultActions = mockMvc.perform(post("/city/munCities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(municipalitiesIds)));

        //assert
        resultActions.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(cities)));

        verify(cityService).findCitiesByMunicipalitiesIds(argumentCaptor.capture());
        List<Long> capturedIds = argumentCaptor.getValue();
        assertThat(capturedIds).containsExactlyElementsOf(municipalitiesIds);
    }
}