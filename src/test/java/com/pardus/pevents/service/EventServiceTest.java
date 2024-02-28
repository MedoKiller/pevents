package com.pardus.pevents.service;

import com.pardus.pevents.exception.EventNotFoundException;
import com.pardus.pevents.model.Event;
import com.pardus.pevents.repo.EventRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    EventRepo eventRepo;

    @InjectMocks
    EventService eventService;

    @Test
    void testDeleteEvent() {
        Long eventId=5L;

        // act
        eventService.deleteEvent(eventId);

        // assert/verify
        verify(eventRepo).deleteEventById(eventId);
    }

    @Test
    void testFindEventById() {
        Event testEvent=new Event();
        testEvent.setName("Test123");

        when(eventRepo.findEventById(1L)).thenReturn(Optional.of(testEvent));

        // act
        Event foundEvent = eventService.findEventById(1L);

        // assert
        assertThat(foundEvent).isNotNull();
        assertThat(foundEvent.getName()).isEqualTo("Test123");
    }

    @Test
    void testFindEventById_ThrowsEventNotFoundException(){
        Long eventId=1L;

        when(eventRepo.findEventById(eventId)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> eventService.findEventById(eventId))
                .isInstanceOf(EventNotFoundException.class)
                .hasMessageContaining("Event with id 1 was not found");
    }
}