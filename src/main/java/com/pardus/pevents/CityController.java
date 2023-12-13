package com.pardus.pevents;

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
    public ResponseEntity<List<City>> getAllCities(){
        List<City> cities=cityService.findAllCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @PostMapping("/munCities")
    public ResponseEntity<List<City>> getMunicipalitiesCities(@RequestBody List<Integer> municipalitiesIds){
        List<City> cities=cityService.findMunicipalitiesCities(municipalitiesIds);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}
