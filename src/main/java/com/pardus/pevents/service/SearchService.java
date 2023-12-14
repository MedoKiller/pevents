package com.pardus.pevents.service;

import com.pardus.pevents.model.Event;
import com.pardus.pevents.model.Search;
import com.pardus.pevents.repo.SearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class SearchService {

    private final SearchRepo searchRepo;

    @Autowired
    public SearchService(SearchRepo searchRepo) {
        this.searchRepo = searchRepo;
    }

    public List<Event> doSearch(Search search){

        String searchName=(search.getName()!=null) ? "%"+search.getName()+"%" : null;
        ZonedDateTime dateFrom=search.getDateFrom();
        ZonedDateTime dateTo=search.getDateTo();
        String freeEntrance=search.getFreeEntrance();
        List<Integer> cityIds=search.getCityIds();

        return this.searchRepo.doSearch(searchName,dateFrom,dateTo,freeEntrance,cityIds);
    }
}
