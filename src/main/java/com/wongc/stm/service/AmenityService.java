package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Amenity;

public interface AmenityService {
    public List<Amenity> findAll();
    public Optional<Amenity> findById(Long id);
    public boolean existsById(Long id);
    public Amenity update(Amenity Amenity);
    public void deleteById(Long id);
    public Amenity save(Amenity Amenity);
}
