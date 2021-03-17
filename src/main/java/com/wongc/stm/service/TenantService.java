package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Tenant;

public interface TenantService {
    public List<Tenant> findAll();
    public Optional<Tenant> findById(Long id);
    public boolean existsById(Long id);
    public Tenant update(Tenant Tenant);
    public void deleteById(Long id);
    public Tenant save(Tenant Tenant);
}
