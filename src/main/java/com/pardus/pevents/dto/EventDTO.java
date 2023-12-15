package com.pardus.pevents.dto;

import com.pardus.pevents.model.City;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventDTO {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ssXX");

    private Long id;

    private String name;

    private ZonedDateTime dateFrom;

    private ZonedDateTime dateTo;

    private String freeEntrance;

    private CityDTO cityDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = ZonedDateTime.parse(dateFrom, formatter);
    }

    public ZonedDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = ZonedDateTime.parse(dateTo, formatter);
    }

    public String getFreeEntrance() {
        return freeEntrance;
    }

    public void setFreeEntrance(String freeEntrance) {
        this.freeEntrance = freeEntrance;
    }

    public CityDTO getCityDTO() {
        return cityDTO;
    }

    public void setCityDTO(CityDTO cityDTO) {
        this.cityDTO = cityDTO;
    }
}
