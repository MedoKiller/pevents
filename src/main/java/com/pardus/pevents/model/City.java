package com.pardus.pevents.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "grad")
@SequenceGenerator(name = "grad_seq",sequenceName = "grad_id_seq",allocationSize = 1)
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grad_seq")
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name = "naziv")
    private String name;

    @ManyToOne
    @JoinColumn(name = "velicina_grada_id")
    private CitySize citySize;

    @ManyToOne
    @JoinColumn(name = "org_jedinica_id")
    private OrganizationUnit organizationUnit;

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

    public CitySize getCitySize() {
        return citySize;
    }

    public void setCitySize(CitySize citySize) {
        this.citySize = citySize;
    }

    public OrganizationUnit getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(OrganizationUnit organizationUnit) {
        this.organizationUnit = organizationUnit;
    }


}
