package com.pardus.pevents.service;

import com.pardus.pevents.model.OrganizationUnitType;
import com.pardus.pevents.repo.OrganizationUnitTypeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class OrganizationUnitTypeServiceTest {

    @Mock
    OrganizationUnitTypeRepo organizationUnitTypeRepo;

    @InjectMocks
    OrganizationUnitTypeService organizationUnitTypeService;

    @Test
    void findAllOrganizationUnitTypes() {
        List<OrganizationUnitType> organizationUnitTypes=new ArrayList<>();

        when(organizationUnitTypeRepo.findAll()).thenReturn(organizationUnitTypes);

        List<OrganizationUnitType> foundOrgUnitTypes=organizationUnitTypeService.findAllOrganizationUnitTypes();

        assertThat(foundOrgUnitTypes).isNotNull();
    }
}