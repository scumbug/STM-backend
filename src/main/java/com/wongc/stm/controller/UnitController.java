package com.wongc.stm.controller;

import com.wongc.stm.model.Unit;
import com.wongc.stm.service.UnitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('SUPER') or hasRole('ADMIN')")

public class UnitController {
    @Autowired
    UnitServiceImpl service;

    /*
     * Standard CRUD endpoints
     */

    @GetMapping("/units")
    public List<Unit> getUnits() {
        return service.findAll();
    }

    @GetMapping("/units/{id}")
    public Optional<Unit> findById(@PathVariable Long id) {
        Optional<Unit> Unit = service.findById(id);
        if (!Unit.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit not found");
        }
        return Unit;
    }

    @PostMapping("/units")
    public Unit saveUnit(@RequestBody Unit unit) {
        return service.save(unit);
    }

    @PostMapping("/units/bulk")
    public List<Unit> saveAllUnits(@RequestBody ArrayList<Unit> units) {
        return service.saveAll(units);
    }

    @PutMapping("/units")
    public Unit updateUnit(@RequestBody Unit unit) {
        if (!service.existsById(unit.getUnitId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit not found");
        }
        Unit res = service.update(unit);
        if (res == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit not found");
        }
        return res;
    }

    @DeleteMapping("/units/{id}")
    public Map<String, Object> deleteUnitById(@PathVariable Long id) {
        if (!service.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit not found");
        }
        service.deleteById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("status", "Unit deleted");
        return map;
    }

    @GetMapping("/properties/{id}/unit")
    public List<Unit> getUnitsByPropertyId(@PathVariable Long id) {
        return service.findByPropertyId(id);
    }

    @GetMapping("/properties/{id}/unit/free")
    public List<Unit> getUnitsByPropertyIdAndFree(@PathVariable Long id) {
        return service.findFreeUnits(id);
    }

}
