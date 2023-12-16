package com.pardus.pevents.mapper;

import com.pardus.pevents.dto.CityDTO;
import com.pardus.pevents.dto.EventDTO;
import com.pardus.pevents.dto.SearchDTO;
import com.pardus.pevents.model.City;
import com.pardus.pevents.model.Event;
import com.pardus.pevents.model.Search;


public class RequestMapper {

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
        if(eventDTO.getFreeEntrance()!=null){
            event.setFreeEntrance(eventDTO.getFreeEntrance());
        }
        event.setCity(map(eventDTO.getCityDTO()));

        return event;
    }

    public static City map(CityDTO cityDTO){
        City city=new City();
        city.setId(cityDTO.getId());
        city.setName(cityDTO.getName());

        return city;
    }

    public static Event mapUpdate(EventDTO eventDTO){
        Event event=new Event();

        event.setId(eventDTO.getId());
        event.setName(eventDTO.getName());
        event.setDateFrom(eventDTO.getDateFrom());
        event.setDateTo(eventDTO.getDateTo());
        if(eventDTO.getFreeEntrance()!=null){
            event.setFreeEntrance(eventDTO.getFreeEntrance());
        }
        event.setCity(map(eventDTO.getCityDTO()));

        return event;
    }



}
