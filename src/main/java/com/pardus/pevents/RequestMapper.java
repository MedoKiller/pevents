package com.pardus.pevents;

import com.pardus.pevents.dto.EventSimpleSearchDTO;
import com.pardus.pevents.model.EventSimpleSearch;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class RequestMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ssXX");

    public static EventSimpleSearch map(EventSimpleSearchDTO eventSimpleSearchDTO){


        EventSimpleSearch result = new EventSimpleSearch();
        result.setName(eventSimpleSearchDTO.getName());
        result.setDateFrom(eventSimpleSearchDTO.getDateFrom()!=null ? ZonedDateTime.parse(eventSimpleSearchDTO.getDateFrom(),formatter) : null);
        result.setDateTo(eventSimpleSearchDTO.getDateTo()!=null ? ZonedDateTime.parse(eventSimpleSearchDTO.getDateTo(), formatter): null);
        result.setFreeEntrance(eventSimpleSearchDTO.getFreeEntrance());
        result.setCityId(eventSimpleSearchDTO.getCityId());

        return result;
    }

}
