package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Unit;
import com.wongc.stm.repository.UnitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitRepository repository;

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
    public Unit update(Unit Unit) {
        Optional<Unit> res = repository.findById(Unit.getUnitId());
        if(!res.isPresent())
            return null;
        Unit tmp = res.get();
        return repository.save(tmp);
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
    public List<Unit> findByPropertyId(Long id) {
        return repository.findByPropertyId(id);
    }

}
