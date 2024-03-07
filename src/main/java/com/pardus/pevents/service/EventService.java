package com.pardus.pevents.service;

import com.pardus.pevents.exception.EventNotFoundException;
import com.pardus.pevents.model.Event;
import com.pardus.pevents.model.Search;
import com.pardus.pevents.repo.EventRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.TimeZone;

@Service
public class EventService {

    private final EventRepo eventRepo;

    @Autowired
    public EventService(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    public Event addEvent(Event event){
        return eventRepo.save(event);
    }

    public List<Event> findAllEvents(){
        return eventRepo.findAll();
    }

    public Event updateEvent(Event event){
        return eventRepo.save(event);
    }

    @Transactional
    public void deleteEvent(Long id){
        eventRepo.deleteEventById(id);
    }

    public Event findEventById(Long id){
        return eventRepo.findEventById(id).
                orElseThrow(()->new EventNotFoundException("Event with id "+id+" was not found"));
    }

}
