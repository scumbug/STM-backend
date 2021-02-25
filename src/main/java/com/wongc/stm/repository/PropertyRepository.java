package com.wongc.stm.repository;

import com.wongc.stm.model.Property;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PropertyRepository extends PagingAndSortingRepository<Property,Long>{
    
}
