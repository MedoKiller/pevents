package com.pardus.pevents;

import com.pardus.pevents.model.CitySize;
import com.pardus.pevents.service.CitySizeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city_size")
public class CitySizeController {

    private final CitySizeService citySizeService;


    public CitySizeController(CitySizeService citySizeService) {
        this.citySizeService = citySizeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CitySize>> getAllCitySizes(){
        List<CitySize> citySizes=citySizeService.findAllCitySizes();
        return new ResponseEntity<>(citySizes, HttpStatus.OK);
    }
}
