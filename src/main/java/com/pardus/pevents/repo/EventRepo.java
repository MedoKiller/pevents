package com.pardus.pevents.repo;

import com.pardus.pevents.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepo extends JpaRepository<Event, Long> {

    void deleteEventById(Long id);

    Optional<Event> findEventById(Long id);


}
