package com.wongc.stm.service;

import com.wongc.stm.model.Property;
import com.wongc.stm.model.enums.PropertyStatus;

import java.util.List;
import java.util.Optional;

public interface PropertyService {
    List<Property> findAll();
    Optional<Property> findById(Long id);
    boolean existsById(Long id);
    Property update(Property property);
    void deleteById(Long id);
    Property save(Property Property);
    Long checkTotalLeasedUnits(Long propertyId);

    List<Property> findByPropertyStatus(PropertyStatus active);
}
