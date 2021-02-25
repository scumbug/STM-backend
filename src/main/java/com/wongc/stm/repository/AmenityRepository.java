package com.wongc.stm.repository;

import com.wongc.stm.model.Amenity;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AmenityRepository extends PagingAndSortingRepository<Amenity, Long> {
    
}
