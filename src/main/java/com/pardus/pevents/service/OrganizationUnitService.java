package com.pardus.pevents.service;

import com.pardus.pevents.model.OrganizationUnit;
import com.pardus.pevents.repo.OrganizationUnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationUnitService {

    private final OrganizationUnitRepo organizationUnitRepo;

    @Value("${app.regionId}")
    private Long REGION_ID;

    @Autowired
    public OrganizationUnitService(OrganizationUnitRepo organizationUnitRepo) {
        this.organizationUnitRepo = organizationUnitRepo;
    }

    public List<OrganizationUnit> findAllOrganizationUnits(){
        return organizationUnitRepo.findAll();
    }


    public List<OrganizationUnit> findAllRegions() {
        return organizationUnitRepo.findAllRegions(REGION_ID);
    }

    public List<OrganizationUnit> findRegionMunicipalities(List<Long> regionIds){
        return organizationUnitRepo.findRegionMunicipalities(regionIds);
    }
}
