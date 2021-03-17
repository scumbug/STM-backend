package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Amenity;
import com.wongc.stm.repository.AmenityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmenityServiceImpl implements AmenityService {

    @Autowired
    private AmenityRepository repository;

    @Override
    public List<Amenity> findAll() {
        return (List<Amenity>) repository.findAll();
    }

    @Override
    public Optional<Amenity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Amenity update(Amenity Amenity) {
        Optional<Amenity> res = repository.findById(Amenity.getAmenityId());
        if (res.isEmpty())
            return null;
        Amenity tmp = res.get();
        return repository.save(tmp);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Amenity save(Amenity Amenity) {
		return repository.save(Amenity);
	}

}
