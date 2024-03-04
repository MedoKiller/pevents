package com.pardus.pevents.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pardus.pevents.dto.EventDTO;
import com.pardus.pevents.dto.SearchDTO;
import com.pardus.pevents.mapper.RequestMapper;
import com.pardus.pevents.mapper.ResponseMapper;
import com.pardus.pevents.model.Event;
import com.pardus.pevents.model.Search;
import com.pardus.pevents.service.SearchService;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = SearchController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchService searchService;

    @MockBean
    private ResponseMapper responseMapper;

    @MockBean
    private RequestMapper requestMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void searchEvents() throws Exception {
        SearchDTO searchDTO = new SearchDTO();
        Search search = new Search();
        List<Event> events = Arrays.asList(new Event(), new Event());
        List<EventDTO> eventDTOS = Arrays.asList(new EventDTO(), new EventDTO());

        when(requestMapper.map(any(SearchDTO.class))).thenReturn(search);
        when(searchService.doSearch(search)).thenReturn(events);
        when(responseMapper.mapEvents(events)).thenReturn(eventDTOS);

        ResultActions resultActions=mockMvc.perform(post("/search/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(searchDTO)));

        resultActions.andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(eventDTOS)))
                .andDo(MockMvcResultHandlers.print());
    }
}