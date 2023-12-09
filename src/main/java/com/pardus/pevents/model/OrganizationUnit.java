package com.pardus.pevents.model;

import jakarta.persistence.*;

@Entity
@Table(name = "organizacijska_jedinica")
@SequenceGenerator(name = "oj_seq",sequenceName = "organizacijska_jedinica_id_seq",allocationSize = 1)
public class OrganizationUnit {

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

}
