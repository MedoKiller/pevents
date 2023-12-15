package com.pardus.pevents.repo;

import com.pardus.pevents.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface SearchRepo extends JpaRepository<Event, Long> {

    @Query(nativeQuery = true, value =
            "SELECT * FROM događaj e WHERE " +
                    "(lower(e.naziv) LIKE COALESCE(?1, lower(e.naziv))) AND " +
                    "(e.vrijeme_od >= COALESCE(?2, e.vrijeme_od)) AND " +
                    "(e.vrijeme_do <= COALESCE(?3, e.vrijeme_do)) AND " +
                    "(e.slobodan_ulaz = COALESCE(?4, e.slobodan_ulaz)) AND " +
                    "(e.grad_id IN (?5) OR (?5) IS NULL)")
    List<Event> doSearch(String searchName, ZonedDateTime dateFrom, ZonedDateTime dateTo,String freeEntrance, List<Integer> cityIds);

}
