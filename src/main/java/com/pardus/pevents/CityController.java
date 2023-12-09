package com.pardus.pevents;

import com.pardus.pevents.model.City;
import com.pardus.pevents.model.CitySize;
import com.pardus.pevents.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
