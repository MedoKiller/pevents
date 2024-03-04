package com.pardus.pevents.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pardus.pevents.dto.EventDTO;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private EventDTO requestEventDTO;
    private EventDTO responseEventDTO;

    @BeforeEach
    void setUp() {
        this.event1=new Event();
        this.event1.setName("TestEvent1");
        this.event2=new Event();
        this.event2.setName("TestEvent2");
        this.requestEventDTO=new EventDTO();
        this.requestEventDTO.setName("TestRequestEventDTO");
        this.responseEventDTO=new EventDTO();
        this.responseEventDTO.setName("TestResponseEventDTO");
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
    void testAddEvent() throws Exception {

        // any() used because request and response mapper methods create new objects
        // so mockito thinks different objects are used, and it fails the test on verify
        // example when verify fails ---
        // { when(requestMapper.map(requestEventDTO)).thenReturn(event1);
        // verify(requestMapper, times(1)).map(requestEventDTO)); }


        when(requestMapper.map(any(EventDTO.class))).thenReturn(event1);
        when(eventService.addEvent(event1)).thenReturn(event1);
        when(responseMapper.map(any(Event.class))).thenReturn(responseEventDTO);

        ResultActions resultActions=mockMvc.perform(post("/event/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestEventDTO)));

        resultActions.andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(responseEventDTO)))
                .andDo(MockMvcResultHandlers.print());

        verify(requestMapper, times(1)).map(any(EventDTO.class));
        verify(eventService, times(1)).addEvent(event1);
        verify(responseMapper, times(1)).map(any(Event.class));

    }

    @Test
    void testUpdateEvent() throws Exception {
        when(requestMapper.mapUpdate(any(EventDTO.class))).thenReturn(event1);
        when(eventService.updateEvent(event1)).thenReturn(event1);
        when(responseMapper.map(any(Event.class))).thenReturn(responseEventDTO);

        ResultActions resultActions=mockMvc.perform(put("/event/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestEventDTO)));

        resultActions.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseEventDTO)))
                .andDo(MockMvcResultHandlers.print());

        verify(requestMapper, times(1)).mapUpdate(any(EventDTO.class));
        verify(eventService, times(1)).updateEvent(event1);
        verify(responseMapper, times(1)).map(any(Event.class));
    }

    @Test
    void testDeleteEvent() throws Exception {
        Long eventId=1L;

        doNothing().when(eventService).deleteEvent(eventId);

        ResultActions resultActions=mockMvc.perform(delete("/event/delete/{id}",eventId)
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());
    }
}