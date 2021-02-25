package com.wongc.stm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.wongc.stm.model.Unit;
import com.wongc.stm.service.UnitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/units")
public class UnitController {
    @Autowired
    private UnitService service;
    
    /*
     * Standard CRUD endpoints
     */

    @GetMapping("/")
    public List<Unit> getUnits() {
        return (List<Unit>) service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Unit> findById(@PathVariable Long id) {
        Optional<Unit> Unit = service.findById(id);
        if(Unit.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Unit not found");
        }
        return Unit;
    }

    @PostMapping("/{id}")
    public Unit saveUnit(@RequestBody Unit Unit) {
        Unit res = service.save(Unit);
        return res;
    }

    @PutMapping("/{id}")
    public Unit updatUnit(@RequestBody Unit Unit) {
        if(!service.existsById(Unit.getUnitId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Unit not found");
        }
        Unit res = service.update(Unit);
        if(res == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Unit not found");
        }
        return res;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUnitById(@PathVariable Long id){
        if(!service.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Unit not found");
        }
        service.deleteById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status","Unit deleted");
        return map;
    }

}
