package com.pardus.pevents;

import com.pardus.pevents.dto.EventSimpleSearchDTO;
import com.pardus.pevents.model.Event;
import com.pardus.pevents.model.EventSimpleSearch;
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
    public ResponseEntity<Event> addEvent(@RequestBody Event event){

        Event newEvent=eventService.addEvent(event);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event){
        Event updateEvent=eventService.updateEvent(event);
        return new ResponseEntity<>(updateEvent, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") Long id){
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/find/simple")
    public ResponseEntity<List<Event>> addEvent(@RequestBody EventSimpleSearchDTO eventDto){
        EventSimpleSearch eventSimpleSearch=RequestMapper.map(eventDto);
        List<Event> events=eventService.findSimpleSearch(eventSimpleSearch);
        return new ResponseEntity<>(events, HttpStatus.CREATED);
    }


}
