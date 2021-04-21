package com.wongc.stm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.wongc.stm.dto.PropertyUpdateDTO;
import com.wongc.stm.model.Property;
import com.wongc.stm.model.enums.PropertyStatus;
import com.wongc.stm.service.PropertyServiceImpl;

import org.modelmapper.ModelMapper;
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
@RequestMapping("/api/properties")
@PreAuthorize("hasRole('SUPER') or hasRole('ADMIN')")
public class PropertyController {
    @Autowired
    private PropertyServiceImpl service;

    @Autowired
    private ModelMapper modelMapper;

    /*
     * Standard CRUD endpoints
     */

    @PreAuthorize("hasRole('SALES') or hasRole('SUPER') or hasRole('ADMIN')")
    @GetMapping("")
    public List<Property> getProperties() {
        return service.findByPropertyStatus(PropertyStatus.ACTIVE);
    }

    @GetMapping("/{id}")
    public Optional<Property> findById(@PathVariable Long id) {
        Optional<Property> Property = service.findById(id);
        if(!Property.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Property not found");
        }
        return Property;
    }

    @PostMapping("")
    public Property saveProperty(@RequestBody Property Property) {
        Property res = service.save(Property);
        return res;
    }

    @PutMapping("")
    public Property updateProperty(@RequestBody Property property) {
        if(!service.existsById(property.getPropertyId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Property not found");
        }
        Property res = service.update(property);
        if(res == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Property not found");
        }
        return res;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deletePropertyById(@PathVariable Long id){
        if(!service.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Property not found");
        }
        service.deleteById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status","Property deleted");
        return map;
    }

    @GetMapping("/{id}/leasecount")
    public Long findLeaseCount(@PathVariable Long id) {
        return service.checkTotalLeasedUnits(id);
    }

}
