package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Payment;

public interface PaymentService {
    public List<Payment> findAll();
    public Optional<Payment> findById(Long id);
    public boolean existsById(Long id);
    public Payment update(Payment Payment);
    public void deleteById(Long id);
    public Payment save(Payment Payment);

    List<Payment> findPaymentsForTenant(Long tenantId, Long unitId);
}
