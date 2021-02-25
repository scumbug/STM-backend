package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Property;
import com.wongc.stm.repository.PropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository repository;

    public List<Property> findAll() {
        return (List<Property>) repository.findAll();
    }

    public Optional<Property> findById(Long id) {
        return repository.findById(id);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public Property update(Property Property) {
        Optional<Property> res = repository.findById(Property.getPropertyId());
        if(res.isEmpty())
            return null;
        Property tmp = res.get();
        return repository.save(tmp);
    }

	public void deleteById(Long id) {
        repository.deleteById(id);
	}

	public Property save(Property Property) {
		return repository.save(Property);
	}
}
