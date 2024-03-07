package com.pardus.pevents.dto;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;


public class EventDTO {


    private Long id;

    private String name;

    private OffsetDateTime dateFrom;

    private OffsetDateTime dateTo;

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

    public OffsetDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(OffsetDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public OffsetDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(OffsetDateTime dateTo) {
        this.dateTo = dateTo;
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
