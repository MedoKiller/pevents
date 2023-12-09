package com.pardus.pevents;

import com.pardus.pevents.model.OrganizationUnit;
import com.pardus.pevents.model.OrganizationUnitType;
import com.pardus.pevents.service.OrganizationUnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
