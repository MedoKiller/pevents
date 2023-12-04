package com.pardus.pevents.model;


import jakarta.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "događaj")
@SequenceGenerator(name = "event_seq",sequenceName = "događaj_id_seq",allocationSize = 1)
public class Event implements Serializable {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ssXX");

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name = "naziv")
    private String name;

    @Column(name = "vrijeme_od")
    private ZonedDateTime dateFrom;

    @Column(name = "vrijeme_do")
    private ZonedDateTime dateTo;

    @Column(name = "slobodan_ulaz")
    private String freeEntrance;

    @ManyToOne
    @JoinColumn(name = "grad_id")
    private City city;

    public Event() {}

    public Event(String name, ZonedDateTime dateFrom, ZonedDateTime dateTo, String freeEntrance) {
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
