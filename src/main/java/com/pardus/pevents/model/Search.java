package com.pardus.pevents.model;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public class Search {


    private String name;

    private OffsetDateTime dateFrom;

    private OffsetDateTime dateTo;

    private String freeEntrance;

    private List<Long> cityIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getDateFrom() {
        return dateFrom;
    }


    public OffsetDateTime getDateTo() {
        return dateTo;
    }

    public void setDateFrom(OffsetDateTime dateFrom) {
        this.dateFrom = dateFrom;
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

    public List<Long> getCityIds() {
        return cityIds;
    }

    public void setCityIds(List<Long> cityIds) {
        this.cityIds = cityIds;
    }
}
