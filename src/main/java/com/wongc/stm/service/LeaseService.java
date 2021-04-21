package com.wongc.stm.service;

import com.wongc.stm.dto.UnitRent;
import com.wongc.stm.model.Contact;
import com.wongc.stm.model.Lease;

import java.util.List;
import java.util.Optional;

public interface LeaseService {
    List<Lease> findAll();
    Optional<Lease> findById(Long id);
    boolean existsById(Long id);
    Lease update(Lease Lease);
    void deleteById(Long id);
    Lease save(Lease Lease);
    Lease convertTenant(Lease lease, List<Contact> contacts);

    Optional<Lease> findByUnitId(Long id);

    List<UnitRent> getHistoricalRentByUnitId(Long id);
}
