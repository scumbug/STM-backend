package com.wongc.stm.controller;

import com.wongc.stm.model.Amenity;
import com.wongc.stm.service.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/amenities")
@PreAuthorize("hasRole('SUPER') or hasRole('ADMIN') or hasRole('SALES')")
public class AmenityController {
    @Autowired
    private AmenityService amenityService;

    @PreAuthorize("hasRole('SUPER') or hasRole('TENANT')")
    @GetMapping("")
    public List<Amenity> getAmenities() {
        return amenityService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Amenity> getAmenity(@PathVariable Long id){
        return amenityService.findById(id);
    }

    @PostMapping("")
    public Amenity saveAmenity(@RequestBody Amenity amenity) {
        return amenityService.save(amenity);
    }

}
