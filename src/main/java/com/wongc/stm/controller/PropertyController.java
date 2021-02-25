package com.wongc.stm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.wongc.stm.model.Property;
import com.wongc.stm.service.PropertyService;

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
@RequestMapping("/properties")
public class PropertyController {
    @Autowired
    private PropertyService service;

    /*
     * Standard CRUD endpoints
     */

    // TODO: implement paging
    @GetMapping("/")
    public List<Property> getProperties() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Property> findById(@PathVariable Long id) {
        Optional<Property> Property = service.findById(id);
        if(Property.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Property not found");
        }
        return Property;
    }

    @PostMapping("/{id}")
    public Property saveProperty(@RequestBody Property Property) {
        Property res = service.save(Property);
        return res;
    }

    @PutMapping("/{id}")
    public Property updatProperty(@RequestBody Property Property) {
        if(!service.existsById(Property.getPropertyId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Property not found");
        }
        Property res = service.update(Property);
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

}
