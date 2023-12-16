package com.pardus.pevents;

import com.pardus.pevents.dto.EventDTO;
import com.pardus.pevents.dto.SearchDTO;
import com.pardus.pevents.mapper.RequestMapper;
import com.pardus.pevents.mapper.ResponseMapper;
import com.pardus.pevents.model.Event;
import com.pardus.pevents.model.Search;
import com.pardus.pevents.service.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;


    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @PostMapping("/filter")
    public ResponseEntity<List<EventDTO>> addEvent(@RequestBody SearchDTO searchDTO){
        Search search= RequestMapper.map(searchDTO);
        List<Event> events=searchService.doSearch(search);
        List<EventDTO> respEvents = ResponseMapper.mapEvents(events);
        return new ResponseEntity<>(respEvents, HttpStatus.CREATED);
    }


}
