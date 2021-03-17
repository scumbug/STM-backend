package com.wongc.stm.repository;

import com.wongc.stm.model.Unit;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UnitRepository extends PagingAndSortingRepository<Unit, Long>{
    List<Unit> findByPropertyId(Long propertyId);
}
