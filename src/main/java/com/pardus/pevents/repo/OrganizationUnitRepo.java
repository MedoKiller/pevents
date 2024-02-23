package com.pardus.pevents.repo;

import com.pardus.pevents.model.OrganizationUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizationUnitRepo extends JpaRepository<OrganizationUnit,Long> {

    @Query(nativeQuery = true, value = "select * from organizacijska_jedinica where tip_org_jedinica_id = :regionId")
    List<OrganizationUnit> findAllRegions(@Param("regionId") Long regionId);


    @Query(nativeQuery = true, value = "select * from organizacijska_jedinica where parent_id in :regionIds")
    List<OrganizationUnit> findRegionMunicipalities(@Param("regionIds") List<Long> regionIds);
}
