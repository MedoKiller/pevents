package com.pardus.pevents.service;

import com.pardus.pevents.model.OrganizationUnit;
import com.pardus.pevents.repo.OrganizationUnitRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class OrganizationUnitServiceTest {

    @Mock
    OrganizationUnitRepo organizationUnitRepo;

    @InjectMocks
    OrganizationUnitService organizationUnitService;

    @Value("${app.regionId}")
    private Long REGION_ID;

    @Test
    void testFindAllOrganizationUnits() {
        List<OrganizationUnit> organizationUnits=new ArrayList<>();

        when(organizationUnitRepo.findAll()).thenReturn(organizationUnits);

        List<OrganizationUnit> foundOrgUnits = organizationUnitService.findAllOrganizationUnits();

        assertThat(foundOrgUnits).isNotNull();
    }

    @Test
    void testFindAllRegions() {
        List<OrganizationUnit> organizationUnits=new ArrayList<>();

        when(organizationUnitRepo.findAllRegions(REGION_ID)).thenReturn(organizationUnits);

        List<OrganizationUnit> foundOrganizationUnits=organizationUnitService.findAllRegions();

        assertThat(foundOrganizationUnits).isNotNull();

    }

    @Test
    void testFindRegionMunicipalities() {
        List<OrganizationUnit> organizationUnits=new ArrayList<>();

        when(organizationUnitRepo.findRegionMunicipalities(List.of(1L,2L))).thenReturn(organizationUnits);

        List<OrganizationUnit> foundOrganizationUnits=organizationUnitService.findRegionMunicipalities(List.of(1L,2L));

        assertThat(foundOrganizationUnits).isNotNull();
    }
}