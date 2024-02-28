package com.pardus.pevents.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pardus.pevents.model.CitySize;
import com.pardus.pevents.service.CitySizeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = CitySizeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class CitySizeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CitySizeService citySizeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllCitySizes() throws Exception {
        CitySize citySize1=new CitySize();
        citySize1.setName("TestCitySize");

        CitySize citySize2=new CitySize();
        citySize2.setName("TestCitySize2");

        List<CitySize> citySizes= Arrays.asList(citySize1,citySize2);

        when(citySizeService.findAllCitySizes()).thenReturn(citySizes);

        ResultActions resultActions=mockMvc.perform(get("/city_size/all")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(citySizes)))
                .andDo(MockMvcResultHandlers.print());

        verify(citySizeService,times(1)).findAllCitySizes();
    }
}