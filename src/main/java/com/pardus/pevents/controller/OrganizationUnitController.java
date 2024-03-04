package com.pardus.pevents.controller;

import com.pardus.pevents.model.OrganizationUnit;
import com.pardus.pevents.service.OrganizationUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/org_unit")
public class OrganizationUnitController {

    private final OrganizationUnitService organizationUnitService;


    public OrganizationUnitController(OrganizationUnitService organizationUnitService) {
        this.organizationUnitService = organizationUnitService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrganizationUnit>> getAllOrganizationUnits(){
        List<OrganizationUnit> organizationUnits=organizationUnitService.findAllOrganizationUnits();
        return new ResponseEntity<>(organizationUnits, HttpStatus.OK);
    }

    @GetMapping("/allRegions")
    public ResponseEntity<List<OrganizationUnit>> getAllRegions(){
        List<OrganizationUnit> organizationUnits=organizationUnitService.findAllRegions();
        return new ResponseEntity<>(organizationUnits, HttpStatus.OK);
    }

    @PostMapping("/regionMunicipalities")
    public ResponseEntity<List<OrganizationUnit>> getRegionMunicipalities(@RequestBody List<Long> regionIds){
        List<OrganizationUnit> organizationUnits=organizationUnitService.findRegionMunicipalities(regionIds);
        return new ResponseEntity<>(organizationUnits, HttpStatus.OK);
    }

}
