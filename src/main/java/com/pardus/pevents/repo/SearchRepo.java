package com.pardus.pevents.repo;

import com.pardus.pevents.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public interface SearchRepo extends JpaRepository<Event, Long> {

    @Query(nativeQuery = true, value =
            "SELECT * FROM događaj e WHERE \n" +
                    "    (e.naziv LIKE COALESCE(?1, e.naziv) OR (null IS NULL AND e.naziv IS NULL)) AND \n" +
                    "    (e.vrijeme_od >= COALESCE(?2, e.vrijeme_od) OR (null IS NULL AND e.vrijeme_od IS NULL)) AND \n" +
                    "    (e.vrijeme_do <= COALESCE(?3, e.vrijeme_do) OR (null IS NULL AND e.vrijeme_do IS NULL)) AND \n" +
                    "    (e.slobodan_ulaz = COALESCE(?4, e.slobodan_ulaz) OR (null IS NULL AND e.slobodan_ulaz IS NULL)) AND \n" +
                    "    (e.grad_id IN (?5) OR (?5) IS NULL)\n")
    List<Event> doSearch(String searchName, OffsetDateTime dateFrom, OffsetDateTime dateTo, String freeEntrance, List<Long> cityIds);

}
