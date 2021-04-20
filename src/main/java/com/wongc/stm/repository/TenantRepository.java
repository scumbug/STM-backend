package com.wongc.stm.repository;

import com.wongc.stm.model.Tenant;

import com.wongc.stm.model.enums.TenantStatus;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantRepository extends PagingAndSortingRepository<Tenant, Long> {


    List<Tenant> findByTenantStatus(TenantStatus tenantStatus);

    Tenant findByUserId(Long userId);
}
