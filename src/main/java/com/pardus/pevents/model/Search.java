package com.pardus.pevents.model;

import java.time.ZonedDateTime;
import java.util.List;

public class Search {


    private String name;

    private ZonedDateTime dateFrom;

    private ZonedDateTime dateTo;

    private String freeEntrance;

    private List<Integer> cityIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getDateFrom() {
        return dateFrom;
    }


    public ZonedDateTime getDateTo() {
        return dateTo;
    }

    public void setDateFrom(ZonedDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(ZonedDateTime dateTo) {
        this.dateTo = dateTo;
    }

    public String getFreeEntrance() {
        return freeEntrance;
    }

    public void setFreeEntrance(String freeEntrance) {
        this.freeEntrance = freeEntrance;
    }

    public List<Integer> getCityIds() {
        return cityIds;
    }

    public void setCityIds(List<Integer> cityIds) {
        this.cityIds = cityIds;
    }
}
