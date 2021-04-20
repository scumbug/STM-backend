package com.wongc.stm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.wongc.stm.dto.UnitRent;
import com.wongc.stm.model.Lease;
import com.wongc.stm.service.LeaseServiceImpl;

import com.wongc.stm.wrapper.LeaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/leases")
@PreAuthorize("hasRole('SUPER') or hasRole('SALES')")
public class LeaseController {
    @Autowired
    private LeaseServiceImpl service;

        /*
     * Standard CRUD endpoints
     */

    @GetMapping("/")
    public List<Lease> getLeases() {
        return (List<Lease>) service.findAll();
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
        Lease res = service.convertTenant(payload.getLease(),payload.getContacts());
        return res;
    }

    @PutMapping("/{id}")
    public Lease updateLease(@RequestBody Lease Lease) {
        if(!service.existsById(Lease.getLeaseId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Lease not found");
        }
        Lease res = service.update(Lease);
        if(res == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Lease not found");
        }
        return res;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteLeaseById(@PathVariable Long id){
        if(!service.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Lease not found");
        }
        service.deleteById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status","Lease deleted");
        return map;
    }

    @GetMapping("/historical/{id}")
    public List<UnitRent> getHistoricalRentByUnitId(@PathVariable Long id) {
        return service.getHistoricalRentByUnitId(id);
    }

}
