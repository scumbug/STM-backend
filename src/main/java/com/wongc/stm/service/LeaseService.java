package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Lease;

public interface LeaseService {
    public List<Lease> findAll();
    public Optional<Lease> findById(Long id);
    public boolean existsById(Long id);
    public Lease update(Lease Lease);
    public void deleteById(Long id);
    public Lease save(Lease Lease);
}
