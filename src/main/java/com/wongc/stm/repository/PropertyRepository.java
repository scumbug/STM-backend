package com.wongc.stm.repository;

import com.wongc.stm.model.Property;

import com.wongc.stm.model.enums.PropertyStatus;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends PagingAndSortingRepository<Property,Long>{
    @Query("SELECT COUNT(*) FROM units INNER JOIN leases on units.unit_id = leases.unit_id WHERE property_id = :propertyId")
    Long getALlLeasedUnitById(@Param("propertyId") Long propertyId);

    List<Property> findByPropertyStatus(PropertyStatus active);
}
