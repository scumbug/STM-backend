package com.wongc.stm.service;

import com.wongc.stm.model.Property;
import com.wongc.stm.model.enums.PropertyStatus;
import com.wongc.stm.repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyRepository repository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<Property> findAll() {
        return (List<Property>) repository.findAll();
    }

    @Override
    public Optional<Property> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Property update(Property property) {
        Property res = repository.findById(property.getPropertyId()).get();
        property.setPropertyId(null);
        modelMapper.map(property,res);
        return repository.save(res);
    }

    @Override
	public void deleteById(Long id) {
        Property res = repository.findById(id).get();
        res.setPropertyStatus(PropertyStatus.ARCHIVED);
        repository.save(res);
	}

    @Override
	public Property save(Property Property) {
		return repository.save(Property);
	}

    @Override
    public Long checkTotalLeasedUnits(Long propertyId) {
        repository.getALlLeasedUnitById(propertyId);
        return repository.getALlLeasedUnitById(propertyId);
    }

    @Override
    public List<Property> findByPropertyStatus(PropertyStatus active) {
        return repository.findByPropertyStatus(active);
    }
}
