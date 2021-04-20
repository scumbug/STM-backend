package com.wongc.stm.repository;

import com.wongc.stm.model.Payment;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long>{

    //@Query("SELECT * FROM payments WHERE (payment_status = 0 AND payment_start_period > :todayPlus15)")
    List<Payment> findByPaymentStatus(int i);

    @Query("SELECT * FROM payments INNER JOIN leases ON payments.lease_id = leases.lease_id WHERE payments.tenant_id = :tenantId AND leases.unit_id = :unitId")
    List<Payment> findPaymentsForTenant(@Param("tenantId") Long tenantId, @Param("unitId") Long unitId);
}
