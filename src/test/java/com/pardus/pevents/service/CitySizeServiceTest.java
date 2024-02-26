package com.pardus.pevents.service;

import com.pardus.pevents.model.CitySize;
import com.pardus.pevents.repo.CitySizeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class CitySizeServiceTest {

    @Mock
    CitySizeRepo citySizeRepo;

    @InjectMocks
    CitySizeService citySizeService;

    @Test
    void testFindAllCitySizes() {
        // arrange
        List<CitySize> citySizes=new ArrayList<>();

        when(citySizeRepo.findAll()).thenReturn(citySizes);

        // act
        List<CitySize> foundCitySizes=citySizeService.findAllCitySizes();

        // assert
        assertThat(foundCitySizes).isNotNull();
    }
}