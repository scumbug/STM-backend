package com.wongc.stm.repository;

import com.wongc.stm.model.Unit;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitRepository extends PagingAndSortingRepository<Unit, Long>{
    List<Unit> findByPropertyId(Long propertyId);

    @Query("SELECT * FROM units LEFT JOIN leases on units.unit_id = leases.unit_id WHERE leases.lease_id IS NULL AND units.property_id = :propertyId")
    List<Unit> findByPropertyIdAndFree(@Param("propertyId") Long propertyId);
}
