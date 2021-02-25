package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Amenity;
import com.wongc.stm.repository.AmenityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmenityService {
    @Autowired
    private AmenityRepository repository;

    public List<Amenity> findAll() {
        return (List<Amenity>) repository.findAll();
    }

    public Optional<Amenity> findById(Long id) {
        return repository.findById(id);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public Amenity update(Amenity Amenity) {
        Optional<Amenity> res = repository.findById(Amenity.getAmenityId());
        if (res.isEmpty())
            return null;
        Amenity tmp = res.get();
        return repository.save(tmp);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Amenity save(Amenity Amenity) {
		return repository.save(Amenity);
	}

}
