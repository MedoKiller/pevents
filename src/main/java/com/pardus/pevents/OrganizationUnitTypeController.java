package com.pardus.pevents;

import com.pardus.pevents.model.OrganizationUnitType;
import com.pardus.pevents.service.OrganizationUnitTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/org_unit_type")
public class OrganizationUnitTypeController {

    private final OrganizationUnitTypeService organizationUnitTypeService;


    public OrganizationUnitTypeController(OrganizationUnitTypeService organizationUnitTypeService) {
        this.organizationUnitTypeService = organizationUnitTypeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrganizationUnitType>> getAllEvents(){
        List<OrganizationUnitType> organizationUnitTypes=organizationUnitTypeService.findAllOrganizationUnitTypes();
        return new ResponseEntity<>(organizationUnitTypes, HttpStatus.OK);
    }
}
