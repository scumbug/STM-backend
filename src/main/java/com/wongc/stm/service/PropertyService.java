package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Property;

public interface PropertyService {
    public List<Property> findAll();
    public Optional<Property> findById(Long id);
    public boolean existsById(Long id);
    public Property update(Property Property);
    public void deleteById(Long id);
    public Property save(Property Property);
}
