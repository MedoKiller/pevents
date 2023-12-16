package com.pardus.pevents;

import com.pardus.pevents.dto.CityDTO;
import com.pardus.pevents.dto.EventDTO;
import com.pardus.pevents.dto.SearchDTO;
import com.pardus.pevents.model.City;
import com.pardus.pevents.model.Event;
import com.pardus.pevents.model.Search;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class RequestMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ssXX");

    public static Search map(SearchDTO searchDTO){

        Search result = new Search();
        result.setName(searchDTO.getEventName());
        result.setDateFrom(searchDTO.getDateFrom());
        result.setDateTo(searchDTO.getDateTo());
        result.setFreeEntrance(searchDTO.getFreeEntrance());
        result.setCityIds(searchDTO.getCityIds());

        return result;
    }

    public static Event map(EventDTO eventDTO){
        Event event=new Event();
        event.setName(eventDTO.getName());
        event.setDateFrom(eventDTO.getDateFrom());
        event.setDateTo(eventDTO.getDateTo());
        event.setFreeEntrance(eventDTO.getFreeEntrance());
        event.setCity(map(eventDTO.getCityDTO()));

        return event;
    }

    public static City map(CityDTO cityDTO){
        City city=new City();
        city.setId(cityDTO.getId());
        city.setName(cityDTO.getName());

        return city;
    }




}
