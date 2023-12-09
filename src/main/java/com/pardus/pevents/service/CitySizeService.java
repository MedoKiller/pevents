package com.pardus.pevents.service;

import com.pardus.pevents.model.CitySize;
import com.pardus.pevents.repo.CitySizeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitySizeService {

    private final CitySizeRepo citySizeRepo;

    @Autowired
    public CitySizeService(CitySizeRepo citySizeRepo) {
        this.citySizeRepo = citySizeRepo;
    }

    public List<CitySize> findAllCitySizes(){
        return citySizeRepo.findAll();
    }

}
