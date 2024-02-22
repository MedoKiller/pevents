package com.pardus.pevents.repo;

import com.pardus.pevents.model.Event;
import com.pardus.pevents.model.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventRepo extends JpaRepository<Event, Long> {

    void deleteEventById(Long id);

    Optional<Event> findEventById(Long id);

}
