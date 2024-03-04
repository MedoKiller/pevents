package com.pardus.pevents.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pardus.pevents.model.OrganizationUnitType;
import com.pardus.pevents.service.OrganizationUnitTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrganizationUnitTypeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class OrganizationUnitTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrganizationUnitTypeService organizationUnitTypeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllOrganizationUnitTypes() throws Exception {
        List<OrganizationUnitType> organizationUnitTypes= Arrays.asList(new OrganizationUnitType(),new OrganizationUnitType());

        when(organizationUnitTypeService.findAllOrganizationUnitTypes()).thenReturn(organizationUnitTypes);

        ResultActions resultActions=mockMvc.perform(get("/org_unit_type/all")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(organizationUnitTypes)));

        verify(organizationUnitTypeService,times(1)).findAllOrganizationUnitTypes();

    }
}