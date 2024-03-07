package com.pardus.pevents.dto;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public class SearchDTO {

    private String eventName;

    private OffsetDateTime dateFrom;

    private OffsetDateTime dateTo;

    private String freeEntrance;

    private List<Long> cityIds;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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
}
