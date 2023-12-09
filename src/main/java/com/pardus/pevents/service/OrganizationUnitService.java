package com.pardus.pevents.service;

import com.pardus.pevents.model.OrganizationUnit;
import com.pardus.pevents.repo.OrganizationUnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationUnitService {

    private final OrganizationUnitRepo organizationUnitRepo;

    @Autowired
    public OrganizationUnitService(OrganizationUnitRepo organizationUnitRepo) {
        this.organizationUnitRepo = organizationUnitRepo;
    }

    public List<OrganizationUnit> findAllOrganizationUnits(){
        return organizationUnitRepo.findAll();
    }
}
