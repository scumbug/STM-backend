package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Unit;

public interface UnitService {
    public List<Unit> findAll();
    public Optional<Unit> findById(Long id);
    public boolean existsById(Long id);
    public Unit update(Unit Unit);
    public void deleteById(Long id);
    public Unit save(Unit Unit);
    public List<Unit> saveAll(List<Unit> Units);
    public List<Unit> findByPropertyId(Long id);

    public List<Unit> findFreeUnits(Long id);
}
