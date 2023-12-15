package com.pardus.pevents.dto;

public class CityDTO {

    private Long id;

    private String name;

    private CitySizeDTO citySizeDTO;

    private OrganizationUnitDTO organizationUnitDTO;

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

    public CitySizeDTO getCitySizeDTO() {
        return citySizeDTO;
    }

    public void setCitySizeDTO(CitySizeDTO citySizeDTO) {
        this.citySizeDTO = citySizeDTO;
    }

    public OrganizationUnitDTO getOrganizationUnitDTO() {
        return organizationUnitDTO;
    }

    public void setOrganizationUnitDTO(OrganizationUnitDTO organizationUnitDTO) {
        this.organizationUnitDTO = organizationUnitDTO;
    }
}
