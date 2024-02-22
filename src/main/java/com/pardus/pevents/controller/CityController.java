package com.pardus.pevents.controller;

import com.pardus.pevents.dto.CityDTO;
import com.pardus.pevents.mapper.ResponseMapper;
import com.pardus.pevents.model.City;
import com.pardus.pevents.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CityDTO>> getAllCities(){
        List<City> cities=cityService.findAllCities();
        List<CityDTO> citiesResp= ResponseMapper.mapCities(cities);
        return new ResponseEntity<>(citiesResp, HttpStatus.OK);
    }

    @PostMapping("/munCities")
    public ResponseEntity<List<City>> getMunicipalitiesCities(@RequestBody List<Long> municipalitiesIds){
        List<City> cities=cityService.findCitiesByMunicipalitiesIds(municipalitiesIds);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}
