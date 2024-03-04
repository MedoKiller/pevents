package com.pardus.pevents.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pardus.pevents.model.OrganizationUnit;
import com.pardus.pevents.service.OrganizationUnitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = OrganizationUnitController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class OrganizationUnitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrganizationUnitService organizationUnitService;

    @Autowired
    private ObjectMapper objectMapper;

    private OrganizationUnit organizationUnit1;
    private OrganizationUnit organizationUnit2;

    @BeforeEach
    void setUp() {
        organizationUnit1=new OrganizationUnit();
        organizationUnit1.setName("OrgUnit1");
        organizationUnit2=new OrganizationUnit();
        organizationUnit2.setName("OrgUnit2");
    }

    @Test
    void testGetAllOrganizationUnits() throws Exception {
        List<OrganizationUnit> organizationUnits= Arrays.asList(organizationUnit1,organizationUnit2);

        when(organizationUnitService.findAllOrganizationUnits()).thenReturn(organizationUnits);

        ResultActions resultActions=mockMvc.perform(get("/org_unit/all")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(organizationUnits)));

        verify(organizationUnitService,times(1)).findAllOrganizationUnits();
    }

    @Test
    void testGetAllRegions() throws Exception {
        List<OrganizationUnit> organizationUnits= Arrays.asList(organizationUnit1,organizationUnit2);

        when(organizationUnitService.findAllRegions()).thenReturn(organizationUnits);

        ResultActions resultActions=mockMvc.perform(get("/org_unit/allRegions")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(organizationUnits)));

        verify(organizationUnitService,times(1)).findAllRegions();
    }

    @Test
    void testGetRegionMunicipalities() throws Exception {
        List<Long> regionIds=Arrays.asList(1L,2L);
        List<OrganizationUnit> organizationUnits= Arrays.asList(organizationUnit1,organizationUnit2);

        when(organizationUnitService.findRegionMunicipalities(regionIds)).thenReturn(organizationUnits);

        ArgumentCaptor<List<Long>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        ResultActions resultActions=mockMvc.perform(post("/org_unit/regionMunicipalities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(regionIds)));

        resultActions.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(organizationUnits)));

        verify(organizationUnitService,times(1)).findRegionMunicipalities(argumentCaptor.capture());
        List<Long> capturedIds = argumentCaptor.getValue();
        assertThat(capturedIds).containsExactlyElementsOf(regionIds);
    }
}