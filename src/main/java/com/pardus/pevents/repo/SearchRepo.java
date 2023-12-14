package com.pardus.pevents.repo;

import com.pardus.pevents.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface SearchRepo extends JpaRepository<Event, Long> {

    @Query(nativeQuery = true, value =
            "select * from događaj where (naziv like :searchName or naziv is null)" +
            " and (vrijeme_od = :dateFrom or vrijeme_od is null) " +
                    "and (vrijeme_do = :dateTo or vrijeme_do is null)" +
                        "and (slobodan_ulaz=:freeEntrance or slobodan_ulaz is null)" +
                            "(and grad_id in :cityIds)")
    List<Event> doSearch(String searchName, ZonedDateTime dateFrom, ZonedDateTime dateTo,String freeEntrance, List<Integer> cityIds);

}
