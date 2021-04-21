package com.wongc.stm.service;

import com.wongc.stm.model.Amenity;

import java.util.List;
import java.util.Optional;

public interface AmenityService {
    List<Amenity> findAll();
    Optional<Amenity> findById(Long id);
    boolean existsById(Long id);
    Amenity update(Amenity Amenity);
    void deleteById(Long id);
    Amenity save(Amenity Amenity);
}
