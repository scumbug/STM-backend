package com.wongc.stm.controller;

import com.wongc.stm.dto.UnitRent;
import com.wongc.stm.model.Lease;
import com.wongc.stm.service.LeaseServiceImpl;
import com.wongc.stm.wrapper.LeaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/leases")
@PreAuthorize("hasRole('SUPER') or hasRole('SALES')")
public class LeaseController {
    @Autowired
    LeaseServiceImpl service;

        /*
     * Standard CRUD endpoints
     */

    @GetMapping("/")
    public List<Lease> getLeases() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Lease> findById(@PathVariable Long id) {
        Optional<Lease> Lease = service.findById(id);
        if(!Lease.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Lease not found");
        }
        return Lease;
    }

    @GetMapping("/unit/{id}")
    public Optional<Lease> findByUnitId(@PathVariable Long id) {
        Optional<Lease> Lease = service.findByUnitId(id);
        if(!Lease.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Lease not found");
        }
        return Lease;
    }

    @PostMapping("")
    public Lease saveLease(@RequestBody LeaseWrapper payload) {
        return service.convertTenant(payload.getLease(),payload.getContacts());
    }

    @PutMapping("/{id}")
    public Lease updateLease(@RequestBody Lease lease) {
        if(!service.existsById(lease.getLeaseId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Lease not found");
        }
        Lease res = service.update(lease);
        if(res == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Lease not found");
        }
        return res;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteLeaseById(@PathVariable Long id){
        if(!service.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Lease not found");
        }
        service.deleteById(id);
        Map<String, String> map = new HashMap<>();
        map.put("status","Lease deleted");
        return map;
    }

    @GetMapping("/historical/{id}")
    public List<UnitRent> getHistoricalRentByUnitId(@PathVariable Long id) {
        return service.getHistoricalRentByUnitId(id);
    }

}
