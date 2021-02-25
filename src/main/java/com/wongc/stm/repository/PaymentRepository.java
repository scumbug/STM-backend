package com.wongc.stm.repository;

import com.wongc.stm.model.Payment;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long>{
    
}
