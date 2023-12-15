package com.pardus.pevents.dto;

public class OrganizationUnitDTO {

    private Long id;

    private String name;

    private OrganizationUnitTypeDTO organizationUnitTypeDTO;

    private OrganizationUnitDTO parentOrganizationUnitDTO;

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

    public OrganizationUnitTypeDTO getOrganizationUnitTypeDTO() {
        return organizationUnitTypeDTO;
    }

    public void setOrganizationUnitTypeDTO(OrganizationUnitTypeDTO organizationUnitTypeDTO) {
        this.organizationUnitTypeDTO = organizationUnitTypeDTO;
    }

    public OrganizationUnitDTO getParentOrganizationUnitDTO() {
        return parentOrganizationUnitDTO;
    }

    public void setParentOrganizationUnitDTO(OrganizationUnitDTO parentOrganizationUnitDTO) {
        this.parentOrganizationUnitDTO = parentOrganizationUnitDTO;
    }
}
