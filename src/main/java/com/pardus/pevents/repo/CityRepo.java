package com.pardus.pevents.repo;

import com.pardus.pevents.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CityRepo extends JpaRepository<City,Long> {
    Optional<City> findCityById(Long id);

    @Query(nativeQuery = true, value = "select * from grad where org_jedinica_id in :munIds")
    List<City> findMunicipalitiesCities(@Param("munIds") List<Integer> munIds);
}
