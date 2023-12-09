package com.pardus.pevents.service;


import com.pardus.pevents.model.OrganizationUnit;
import com.pardus.pevents.model.OrganizationUnitType;
import com.pardus.pevents.repo.OrganizationUnitTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationUnitTypeService {

    private final OrganizationUnitTypeRepo organizationUnitTypeRepo;

    @Autowired
    public OrganizationUnitTypeService(OrganizationUnitTypeRepo organizationUnitTypeRepo) {
        this.organizationUnitTypeRepo = organizationUnitTypeRepo;
    }

    public List<OrganizationUnitType> findAllOrganizationUnitTypes(){
        return organizationUnitTypeRepo.findAll();
    }
}
