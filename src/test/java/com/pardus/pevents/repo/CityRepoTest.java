package com.pardus.pevents.repo;

import com.pardus.pevents.model.City;
import com.pardus.pevents.model.OrganizationUnit;
import com.pardus.pevents.repo.CityRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CityRepoTest {

    @Autowired
    private CityRepo testCityRepo;

    @Autowired
    private OrganizationUnitRepo testOrganizationUnitRepo;

    @AfterEach
    void tearDown() {
        testCityRepo.deleteAll();
        testOrganizationUnitRepo.deleteAll();
    }

    @Test
    public void testFindCityById() {
        // Setup data scenario
        City city = new City();
        city.setName("Test City");
        // Assuming citySize and organizationUnit are correctly set up
        // city.setCitySize(...);
        // city.setOrganizationUnit(...);

        City resultCity = testCityRepo.save(city);

        // Test find by ID
        Optional<City> foundCity = testCityRepo.findCityById(resultCity.getId());
        assertThat(foundCity).isPresent();
        assertThat(foundCity.get().getName()).isEqualTo("Test City");
    }

    @Test
    public void testFindCitiesByMunicipalitiesIds(){  //municipalitiy is organizational unit
        // arrange
        OrganizationUnit orgUnitOne = new OrganizationUnit();
        OrganizationUnit orgUnitTwo = new OrganizationUnit();

        orgUnitOne.setId(1L);
        orgUnitTwo.setId(2L);

        testOrganizationUnitRepo.save(orgUnitOne);
        testOrganizationUnitRepo.save(orgUnitTwo);

        City testCityOne = new City();
        City testCityTwo = new City();

        testCityOne.setOrganizationUnit(orgUnitOne);
        testCityTwo.setOrganizationUnit(orgUnitTwo);

        List<City> testCities = List.of(testCityOne,testCityTwo);

        testCityRepo.saveAll(testCities);

        // act
        List<Long> testMunIds = List.of(orgUnitOne.getId(), orgUnitTwo.getId());

        List<City> resultCities = testCityRepo.findCitiesByMunicipalitiesIds(testMunIds);

        // assert

        assertThat(resultCities).isNotEmpty();
        assertThat(resultCities.size()).isEqualTo(2);
    }

}
