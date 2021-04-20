package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.dto.PropertyUpdateDTO;
import com.wongc.stm.model.Property;
import com.wongc.stm.model.enums.PropertyStatus;

public interface PropertyService {
    public List<Property> findAll();
    public Optional<Property> findById(Long id);
    public boolean existsById(Long id);
    public Property update(Property property);
    public void deleteById(Long id);
    public Property save(Property Property);
    public Long checkTotalLeasedUnits(Long propertyId);

    List<Property> findByPropertyStatus(PropertyStatus active);
}
