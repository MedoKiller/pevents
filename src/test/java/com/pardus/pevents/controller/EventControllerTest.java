package com.pardus.pevents.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pardus.pevents.mapper.RequestMapper;
import com.pardus.pevents.mapper.ResponseMapper;
import com.pardus.pevents.model.Event;
import com.pardus.pevents.service.EventService;
import org.junit.jupiter.api.BeforeEach;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;


@WebMvcTest(controllers = EventController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @MockBean
    private ResponseMapper responseMapper;

    @MockBean
    private RequestMapper requestMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private Event event1;
    private Event event2;

    @BeforeEach
    void setUp() {
        this.event1=new Event();
        this.event1.setName("TestEvent1");
        this.event2=new Event();
        this.event2.setName("TestEvent2");
    }

    @Test
    void testGetAllEvents() throws Exception {
        List<Event> events= Arrays.asList(event1,event2);

        when(eventService.findAllEvents()).thenReturn(events);

        ResultActions resultActions=mockMvc.perform(get("/event/all")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(events)))
                .andDo(MockMvcResultHandlers.print());

        verify(eventService,times(1)).findAllEvents();
    }

    @Test
    void testGetEventById() throws Exception {
        Long eventId=1L;

        when(eventService.findEventById(eventId)).thenReturn(event1);

        ResultActions resultActions=mockMvc.perform(get("/event/find/{id}",eventId)
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(event1)))
                .andDo(MockMvcResultHandlers.print());

        verify(eventService,times(1)).findEventById(eventId);
    }

    @Test
    void testAddEvent() {
    }

    @Test
    void testUpdateEvent() {
    }

    @Test
    void testDeleteEvent() {
    }
}