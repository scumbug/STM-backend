package com.wongc.stm.service;

import com.wongc.stm.model.Unit;
import com.wongc.stm.repository.UnitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    UnitRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Unit> findAll() {
        return (List<Unit>) repository.findAll();
    }

    @Override
    public Optional<Unit> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Unit update(Unit unit) {
        Unit res = repository.findById(unit.getUnitId()).get();
        unit.setUnitId(null);
        modelMapper.map(unit,res);
        return repository.save(res);
    }

    @Override
	public void deleteById(Long id) {
        repository.deleteById(id);
	}

    @Override
	public Unit save(Unit Unit) {
		return repository.save(Unit);
	}

    @Override
    public List<Unit> saveAll(List<Unit> Units) {
        return (List<Unit>) repository.saveAll(Units);
    }

    @Override
    public List<Unit> findByPropertyId(Long id) {
        return repository.findByPropertyId(id);
    }

    @Override
    public List<Unit> findFreeUnits(Long id) {
        return repository.findByPropertyIdAndFree(id);
    }

}
