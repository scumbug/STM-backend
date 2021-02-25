package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Unit;
import com.wongc.stm.repository.UnitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitService {
    @Autowired
    private UnitRepository repository;

    public List<Unit> findAll() {
        return (List<Unit>) repository.findAll();
    }

    public Optional<Unit> findById(Long id) {
        return repository.findById(id);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public Unit update(Unit Unit) {
        Optional<Unit> res = repository.findById(Unit.getUnitId());
        if(res.isEmpty())
            return null;
        Unit tmp = res.get();
        return repository.save(tmp);
    }

	public void deleteById(Long id) {
        repository.deleteById(id);
	}

	public Unit save(Unit Unit) {
		return repository.save(Unit);
	}

}
