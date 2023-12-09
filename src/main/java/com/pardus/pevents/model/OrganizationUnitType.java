package com.pardus.pevents.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tip_organizacijske_jedinice")
@SequenceGenerator(name = "toj_seq",sequenceName = "tip_organizacijske_jedinice_id_seq",allocationSize = 1)
public class OrganizationUnitType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "toj_seq")
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name = "naziv")
    private String name;

    @Column(name = "aktivan")
    private boolean active;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
