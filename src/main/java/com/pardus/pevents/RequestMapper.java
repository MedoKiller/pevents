package com.pardus.pevents;

import com.pardus.pevents.dto.SearchDTO;
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

}
