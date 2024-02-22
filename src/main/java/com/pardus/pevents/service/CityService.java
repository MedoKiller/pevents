package com.pardus.pevents.service;

import com.pardus.pevents.exception.CityNotFoundException;
import com.pardus.pevents.model.City;
import com.pardus.pevents.repo.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepo cityRepo;

    @Autowired
    public CityService(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    public City findCityById(Long id){
        return cityRepo.findCityById(id).
                orElseThrow(()->new CityNotFoundException("City with id "+id+" was not found"));
    }

    public List<City> findAllCities(){
        return cityRepo.findAll();
    }

    public List<City> findCitiesByMunicipalitiesIds(List<Long> munIds){
        return this.cityRepo.findCitiesByMunicipalitiesIds(munIds);
    }
}
