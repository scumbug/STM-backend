package com.wongc.stm.repository;

import com.wongc.stm.model.Tenant;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends PagingAndSortingRepository<Tenant, Long> {
    
}
