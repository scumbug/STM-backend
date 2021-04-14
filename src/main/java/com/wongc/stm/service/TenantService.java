package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Contact;
import com.wongc.stm.model.Tenant;
import com.wongc.stm.model.User;
import com.wongc.stm.model.enums.TenantStatus;
import com.wongc.stm.wrapper.TenantWrapper;

public interface TenantService {
    public List<Tenant> findAll();
    public Optional<Tenant> findById(Long id);
    public boolean existsById(Long id);
    public Tenant update(Tenant Tenant);
    public void deleteById(Long id);
    public Tenant save(Tenant tenant, User user, List<Contact> contact);

    public List<Tenant> findAllPotential();

    public List<Tenant> findAllActive();

    public List<TenantWrapper> aggregate(TenantStatus tenantStatus);

    public Optional<Tenant> convert(Long id);
}
