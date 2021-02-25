package com.wongc.stm.repository;

import com.wongc.stm.model.Lease;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface LeaseRepository extends PagingAndSortingRepository<Lease, Long>{
    
}
