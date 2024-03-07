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
class RequestMapperTest {

    @Autowired
    private RequestMapper requestMapper;

    @BeforeEach
    void setUp() {
        requestMapper=new RequestMapper();
    }

    @Test
    void testMapSearchDTOToSearch() {
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setEventName("Test Event");
        searchDTO.setDateFrom(DateUtils.timestampToZonedDT("01.01.2024 12:00").toOffsetDateTime());
        searchDTO.setDateTo(DateUtils.timestampToZonedDT("02.01.2024 12:00").toOffsetDateTime());
        searchDTO.setFreeEntrance("NE");
        List<Long> cityIds = Arrays.asList(1L, 2L);
        searchDTO.setCityIds(cityIds);

        Search result = requestMapper.map(searchDTO);

        assertThat(result.getName()).isEqualTo(searchDTO.getEventName());
        assertThat(result.getDateFrom()).isEqualTo(searchDTO.getDateFrom());
        assertThat(result.getDateTo()).isEqualTo(searchDTO.getDateTo());
        assertThat(result.getFreeEntrance()).isEqualTo(searchDTO.getFreeEntrance());
        assertThat(result.getCityIds()).isEqualTo(searchDTO.getCityIds());
    }

    @Test
    void testMapEventDTOToEvent() throws ParseException {
        // Given: an EventDTO with known values, including a nested CityDTO
        EventDTO eventDTO = new EventDTO();
        eventDTO.setName("Event Name");
        eventDTO.setDateFrom(DateUtils.timestampToZonedDT("20.02.2024 12:00").toOffsetDateTime());
        eventDTO.setDateTo(DateUtils.timestampToZonedDT("21.02.2024 12:00").toOffsetDateTime());
        eventDTO.setFreeEntrance("NE");
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(1L);
        cityDTO.setName("City Name");
        eventDTO.setCityDTO(cityDTO);

        Event result = requestMapper.map(eventDTO);

        assertThat(result.getName()).isEqualTo(eventDTO.getName());
        assertThat(result.getDateFrom()).isEqualTo(eventDTO.getDateFrom());
        assertThat(result.getDateTo()).isEqualTo(eventDTO.getDateTo());
        assertThat(result.getFreeEntrance()).isEqualTo(eventDTO.getFreeEntrance());
        assertThat(result.getCity()).isNotNull();
        assertThat(result.getCity().getId()).isEqualTo(cityDTO.getId());
        assertThat(result.getCity().getName()).isEqualTo(cityDTO.getName());
    }

    @Test
    void testMapCityDTOToCity() {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(2L);
        cityDTO.setName("Another City");

        City result = requestMapper.map(cityDTO);

        assertThat(result.getId()).isEqualTo(cityDTO.getId());
        assertThat(result.getName()).isEqualTo(cityDTO.getName());
    }

    @Test
    void testMapUpdateEventDTOToEvent() throws ParseException {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(3L);
        eventDTO.setName("Updated Event Name");
        eventDTO.setDateFrom(DateUtils.timestampToZonedDT("10.02.2024 12:00").toOffsetDateTime());
        eventDTO.setDateTo(DateUtils.timestampToZonedDT("11.02.2024 12:00").toOffsetDateTime());
        eventDTO.setFreeEntrance("DA");
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(2L);
        cityDTO.setName("Updated City Name");
        eventDTO.setCityDTO(cityDTO);

        Event result = requestMapper.mapUpdate(eventDTO);

        assertThat(result.getId()).isEqualTo(eventDTO.getId());
        assertThat(result.getName()).isEqualTo(eventDTO.getName());
        assertThat(result.getDateFrom()).isEqualTo(eventDTO.getDateFrom());
        assertThat(result.getDateTo()).isEqualTo(eventDTO.getDateTo());
        assertThat(result.getFreeEntrance()).isEqualTo(eventDTO.getFreeEntrance());
        assertThat(result.getCity()).isNotNull();
        assertThat(result.getCity().getId()).isEqualTo(cityDTO.getId());
        assertThat(result.getCity().getName()).isEqualTo(cityDTO.getName());
    }
}