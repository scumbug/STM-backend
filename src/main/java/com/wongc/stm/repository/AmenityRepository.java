package com.wongc.stm.repository;

import com.wongc.stm.model.Amenity;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityRepository extends PagingAndSortingRepository<Amenity, Long> {
    
}
