package com.pardus.pevents.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private Date dateFrom;
    private Date dateTo;
    private String freeEntrance;

    public Event() {}

    public Event(String name, Date dateFrom, Date dateTo, String freeEntrance) {
        this.name = name;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.freeEntrance = freeEntrance;
    }

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

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getFreeEntrance() {
        return freeEntrance;
    }

    public void setFreeEntrance(String freeEntrance) {
        this.freeEntrance = freeEntrance;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", freeEntrance='" + freeEntrance + '\'' +
                '}';
    }
}
