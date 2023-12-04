package com.pardus.pevents.repo;

import com.pardus.pevents.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepo extends JpaRepository<City,Long> {
    Optional<City> findCityById(Long id);
}
