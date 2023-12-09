package com.pardus.pevents.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "organizacijska_jedinica")
@SequenceGenerator(name = "oj_seq",sequenceName = "organizacijska_jedinica_id_seq",allocationSize = 1)
public class OrganizationUnit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oj_seq")
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name = "naziv")
    private String name;

    @Column(name = "opis")
    private String description;

    @ManyToOne
    @JoinColumn(name = "tip_org_jedinica_id")
    private OrganizationUnitType organizationUnitType;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private OrganizationUnit parentOrganizationUnit;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrganizationUnitType getOrganizationUnitType() {
        return organizationUnitType;
    }

    public void setOrganizationUnitType(OrganizationUnitType organizationUnitType) {
        this.organizationUnitType = organizationUnitType;
    }

    public OrganizationUnit getParentOrganizationUnit() {
        return parentOrganizationUnit;
    }

    public void setParentOrganizationUnit(OrganizationUnit parentOrganizationUnit) {
        this.parentOrganizationUnit = parentOrganizationUnit;
    }
}
