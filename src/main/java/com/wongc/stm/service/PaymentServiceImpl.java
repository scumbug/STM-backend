package com.wongc.stm.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Payment;
import com.wongc.stm.repository.PaymentRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Payment> findAll() {
        return (List<Payment>) repository.findAll();
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Payment update(Payment payment) {
        Payment res = repository.findById(payment.getPaymentId()).get();
        res.setAmount(payment.getAmount());
        res.setPaymentDate(Date.valueOf(LocalDate.now()));
        res.setPaymentProof(payment.getPaymentProof());
        return repository.save(res);
    }

    @Override
	public void deleteById(Long id) {
        repository.deleteById(id);
	}

    @Override
	public Payment save(Payment Payment) {
		return repository.save(Payment);
	}

    @Override
    public List<Payment> findPaymentsForTenant(Long tenantId, Long unitId) {
        return repository.findPaymentsForTenant(tenantId,unitId);
    }

}
