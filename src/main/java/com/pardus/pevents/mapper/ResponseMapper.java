package com.pardus.pevents.mapper;

import com.pardus.pevents.dto.CityDTO;
import com.pardus.pevents.dto.EventDTO;
import com.pardus.pevents.model.City;
import com.pardus.pevents.model.Event;

import java.util.ArrayList;
import java.util.List;

public class ResponseMapper {

    public static EventDTO map(Event event){
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());

        return eventDTO;
    }

    public static List<EventDTO> mapEvents(List<Event> events){
        List<EventDTO> eventDTOS= new ArrayList<>();

        for (Event event : events) {
            EventDTO dto = new EventDTO();

            dto.setId(event.getId());
            dto.setName(event.getName());
            dto.setDateFrom(event.getDateFrom());
            dto.setDateTo(event.getDateTo());
            dto.setFreeEntrance(event.getFreeEntrance());
            dto.setCityDTO(map(event.getCity()));

            eventDTOS.add(dto);
        }

        return eventDTOS;
    }

    public static CityDTO map(City city){
        CityDTO cityDTO=new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());

        return cityDTO;
    }

    public static List<CityDTO> mapCities(List<City> cities){

        List<CityDTO> citiesDTO = new ArrayList<>();

        for(City city:cities){

            CityDTO dto= new CityDTO();
            dto.setId(city.getId());
            dto.setCitySizeDTO(null);
            dto.setName(city.getName());
            dto.setOrganizationUnitDTO(null);

            citiesDTO.add(dto);
        }

        return citiesDTO;
    }

}
