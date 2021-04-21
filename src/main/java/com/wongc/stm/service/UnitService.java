package com.wongc.stm.service;

import com.wongc.stm.model.Unit;

import java.util.List;
import java.util.Optional;

public interface UnitService {
    List<Unit> findAll();
    Optional<Unit> findById(Long id);
    boolean existsById(Long id);
    Unit update(Unit Unit);
    void deleteById(Long id);
    Unit save(Unit Unit);
    List<Unit> saveAll(List<Unit> Units);
    List<Unit> findByPropertyId(Long id);

    List<Unit> findFreeUnits(Long id);
}
