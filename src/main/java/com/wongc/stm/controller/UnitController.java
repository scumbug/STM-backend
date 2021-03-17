package com.wongc.stm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.wongc.stm.model.Unit;
import com.wongc.stm.service.UnitServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class UnitController {
    @Autowired
    private UnitServiceImpl service;

    /*
     * Standard CRUD endpoints
     */

    @GetMapping("/units")
    public List<Unit> getUnits() {
        return (List<Unit>) service.findAll();
    }

    @GetMapping("/units/{id}")
    public Optional<Unit> findById(@PathVariable Long id) {
        Optional<Unit> Unit = service.findById(id);
        if (!Unit.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit not found");
        }
        return Unit;
    }

    @PostMapping("/units/{id}")
    public Unit saveUnit(@RequestBody Unit Unit) {
        Unit res = service.save(Unit);
        return res;
    }

    @PutMapping("/units/{id}")
    public Unit updateUnit(@RequestBody Unit Unit) {
        if (!service.existsById(Unit.getUnitId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit not found");
        }
        Unit res = service.update(Unit);
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
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "Unit deleted");
        return map;
    }

    @GetMapping("/properties/{id}/unit")
    public List<Unit> getUnitsByPropertyId(@PathVariable Long id) {
        return (List<Unit>) service.findByPropertyId(id);
    }

}
