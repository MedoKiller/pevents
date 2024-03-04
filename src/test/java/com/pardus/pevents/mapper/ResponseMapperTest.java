package com.pardus.pevents.mapper;

import com.pardus.pevents.dto.CityDTO;
import com.pardus.pevents.dto.EventDTO;
import com.pardus.pevents.dto.SearchDTO;
import com.pardus.pevents.model.City;
import com.pardus.pevents.model.Event;
import com.pardus.pevents.model.Search;
import com.pardus.pevents.util.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class ResponseMapperTest {

    @Autowired
    private ResponseMapper responseMapper;

    @BeforeEach
    void setUp() {
        responseMapper = new ResponseMapper();
    }

    @Test
    void testMapEventDTOToEvent() {
        Event event = new Event();
        event.setId(1L);
        event.setName("Sample Event");

        EventDTO eventDTO = responseMapper.map(event);

        assertThat(eventDTO.getId()).isEqualTo(event.getId());
        assertThat(eventDTO.getName()).isEqualTo(event.getName());
    }

    @Test
    void testMapEventsToEventDTOs() {
        City city=new City();
        city.setId(1L);
        city.setName("City");

        List<Event> events = Arrays.asList(
                new Event("Event 1",null,null,"NE",city),
                new Event("Event 2",null,null,"DA",city)
        );

        List<EventDTO> eventDTOS = responseMapper.mapEvents(events);

        assertThat(eventDTOS).hasSize(events.size());
        assertThat(eventDTOS.get(0).getName()).isEqualTo(events.get(0).getName());
        assertThat(eventDTOS.get(0).getFreeEntrance()).isEqualTo(events.get(0).getFreeEntrance());
        assertThat(eventDTOS.get(0).getCityDTO().getName()).isEqualTo(events.get(0).getCity().getName());
        assertThat(eventDTOS.get(1).getName()).isEqualTo(events.get(1).getName());
        assertThat(eventDTOS.get(1).getFreeEntrance()).isEqualTo(events.get(1).getFreeEntrance());
        assertThat(eventDTOS.get(1).getCityDTO().getName()).isEqualTo(events.get(0).getCity().getName());
    }

    @Test
    void testMapCityToCityDTO() {
        City city = new City();
        city.setId(1L);
        city.setName("City Name");

        CityDTO cityDTO = responseMapper.map(city);

        assertThat(cityDTO.getId()).isEqualTo(city.getId());
        assertThat(cityDTO.getName()).isEqualTo(city.getName());
    }

    @Test
    void testMapCitiesToCityDTOs() {
        City city = new City();
        city.setId(1L);
        city.setName("City Name");

        City city2 = new City();
        city.setId(2L);
        city.setName("City Name 2");

        List<City> cities = Arrays.asList(city,city2);

        List<CityDTO> cityDTOs = responseMapper.mapCities(cities);

        assertThat(cityDTOs).hasSize(cities.size());
        assertThat(cityDTOs.get(0).getName()).isEqualTo(cities.get(0).getName());
        assertThat(cityDTOs.get(0).getCitySizeDTO()).isNull();
        assertThat(cityDTOs.get(0).getOrganizationUnitDTO()).isNull();
        assertThat(cityDTOs.get(1).getName()).isEqualTo(cities.get(1).getName());
        assertThat(cityDTOs.get(1).getCitySizeDTO()).isNull();
        assertThat(cityDTOs.get(1).getOrganizationUnitDTO()).isNull();
    }
}