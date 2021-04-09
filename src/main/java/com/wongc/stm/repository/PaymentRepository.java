package com.wongc.stm.repository;

import com.wongc.stm.model.Payment;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long>{

    //@Query("SELECT * FROM payments WHERE (payment_status = 0 AND payment_start_period > :todayPlus15)")
    List<Payment> findByPaymentStatus(int i);
}
