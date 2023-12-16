package com.pardus.pevents.model;


import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "događaj")
@SequenceGenerator(name = "event_seq",sequenceName = "događaj_id_seq",allocationSize = 1)
public class Event implements Serializable {

    private static final String DEFAULT_ENTRANCE = "NE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name = "naziv")
    private String name;

    @Column(name = "vrijeme_od")
    private Timestamp dateFrom;

    @Column(name = "vrijeme_do")
    private Timestamp dateTo;

    @Column(name = "slobodan_ulaz")
    private String freeEntrance;

    @ManyToOne
    @JoinColumn(name = "grad_id")
    private City city;

    public Event() {
        this.freeEntrance=DEFAULT_ENTRANCE;
    }

    public Event(String name, Timestamp dateFrom, Timestamp dateTo, String freeEntrance) {
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

    public Timestamp getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Timestamp dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Timestamp getDateTo() {
        return dateTo;
    }

    public void setDateTo(Timestamp dateTo) {
        this.dateTo = dateTo;
    }

    public String getFreeEntrance() {
        return freeEntrance;
    }

    public void setFreeEntrance(String freeEntrance) {
        this.freeEntrance = freeEntrance;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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
