package com.wongc.stm.service;

import com.wongc.stm.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<Payment> findAll();
    Optional<Payment> findById(Long id);
    boolean existsById(Long id);
    Payment update(Payment Payment);
    void deleteById(Long id);
    Payment save(Payment Payment);

    List<Payment> findPaymentsForTenant(Long tenantId, Long unitId);
}
