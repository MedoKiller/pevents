package com.pardus.pevents;

import com.pardus.pevents.dto.EventDTO;
import com.pardus.pevents.mapper.RequestMapper;
import com.pardus.pevents.mapper.ResponseMapper;
import com.pardus.pevents.model.Event;
import com.pardus.pevents.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents(){
        List<Event> events=eventService.findAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long id){
        Event event=eventService.findEventById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<EventDTO> addEvent(@RequestBody EventDTO eventDTO){
        Event newEvent=eventService.addEvent(RequestMapper.map(eventDTO));
        EventDTO respEvent= ResponseMapper.map(newEvent);
        return new ResponseEntity<>(respEvent, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<EventDTO> updateEvent(@RequestBody EventDTO eventDTO){
        Event updateEvent=eventService.updateEvent(RequestMapper.mapUpdate(eventDTO));
        EventDTO respEvent = ResponseMapper.map(updateEvent);
        return new ResponseEntity<>(respEvent, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") Long id){
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
