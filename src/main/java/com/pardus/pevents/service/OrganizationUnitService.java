package com.pardus.pevents.service;

import com.pardus.pevents.model.OrganizationUnit;
import com.pardus.pevents.repo.OrganizationUnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationUnitService {

    private final OrganizationUnitRepo organizationUnitRepo;

    private final Long REGION_ID = 1L;

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

    public List<OrganizationUnit> findRegionMunicipalities(List<Integer> regionIds){
        return organizationUnitRepo.findRegionMunicipalities(regionIds);
    }
}
