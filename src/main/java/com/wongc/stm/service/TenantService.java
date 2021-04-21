package com.wongc.stm.service;

import com.wongc.stm.model.Contact;
import com.wongc.stm.model.Tenant;
import com.wongc.stm.model.User;
import com.wongc.stm.model.enums.TenantStatus;
import com.wongc.stm.wrapper.TenantWrapper;

import java.util.List;
import java.util.Optional;

public interface TenantService {
    List<Tenant> findAll();
    Optional<Tenant> findById(Long id);
    boolean existsById(Long id);
    Tenant update(Tenant Tenant);
    void deleteById(Long id);
    Tenant save(Tenant tenant, User user, List<Contact> contact);

    List<Tenant> findAllPotential();

    List<Tenant> findAllActive();

    List<TenantWrapper> aggregate(TenantStatus tenantStatus);

    Optional<Tenant> convert(Long id);

    List<Tenant> findAllPending();

    Tenant findByUserId(Long id);
}
