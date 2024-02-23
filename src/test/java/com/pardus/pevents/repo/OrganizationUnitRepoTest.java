package com.pardus.pevents.repo;

import com.pardus.pevents.model.OrganizationUnit;
import com.pardus.pevents.model.OrganizationUnitType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class OrganizationUnitRepoTest {

    @Autowired
    private OrganizationUnitRepo testOrgUnitRepo;

    @Autowired
    private OrganizationUnitTypeRepo testOrgUnitTypeRepo;

    @AfterEach
    void tearDown() {
        testOrgUnitRepo.deleteAll();
        testOrgUnitTypeRepo.deleteAll();
    }

    @Test
    void testFindAllRegions() {
        // arrange
        OrganizationUnitType testOrgUnitType = new OrganizationUnitType();

        testOrgUnitType.setId(1L);

        testOrgUnitTypeRepo.save(testOrgUnitType);

        OrganizationUnit testOrgUnitOne = new OrganizationUnit();
        OrganizationUnit testOrgUnitTwo = new OrganizationUnit();

        testOrgUnitOne.setName("Region One");
        testOrgUnitOne.setOrganizationUnitType(testOrgUnitType);
        testOrgUnitTwo.setName("Region Two");
        testOrgUnitTwo.setOrganizationUnitType(testOrgUnitType);

        testOrgUnitRepo.save(testOrgUnitOne);
        testOrgUnitRepo.save(testOrgUnitTwo);

        // act
        List<OrganizationUnit> resultRegions = testOrgUnitRepo.findAllRegions(testOrgUnitType.getId());

        // assert

        assertThat(resultRegions).containsExactlyInAnyOrder(testOrgUnitOne, testOrgUnitTwo);
        assertThat(resultRegions)
                .extracting("name")
                .containsExactlyInAnyOrder("Region One", "Region Two");

    }

    @Test
    void testFindRegionMunicipalities() {
        // arrange
        OrganizationUnitType testOrgUnitType = new OrganizationUnitType();
        testOrgUnitTypeRepo.save(testOrgUnitType);

        OrganizationUnit testParentOrgUnit = new OrganizationUnit();
        OrganizationUnit testChildOrgUnit = new OrganizationUnit();

        testParentOrgUnit.setOrganizationUnitType(testOrgUnitType);
        testChildOrgUnit.setOrganizationUnitType(testOrgUnitType);
        testChildOrgUnit.setParentOrganizationUnit(testParentOrgUnit);

        testOrgUnitRepo.save(testChildOrgUnit);
        testOrgUnitRepo.save(testParentOrgUnit);

        // act
        List<OrganizationUnit> resultRegionMunicipalities = testOrgUnitRepo.findRegionMunicipalities(List.of(testParentOrgUnit.getId()));

        // assert
        assertThat(resultRegionMunicipalities.size()).isEqualTo(1);
        assertThat(resultRegionMunicipalities).containsExactly(testChildOrgUnit);

    }
}