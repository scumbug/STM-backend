package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Payment;
import com.wongc.stm.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;

    public List<Payment> findAll() {
        return (List<Payment>) repository.findAll();
    }

    public Optional<Payment> findById(Long id) {
        return repository.findById(id);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public Payment update(Payment Payment) {
        Optional<Payment> res = repository.findById(Payment.getPaymentId());
        if(res.isEmpty())
            return null;
        Payment tmp = res.get();
        return repository.save(tmp);
    }

	public void deleteById(Long id) {
        repository.deleteById(id);
	}

	public Payment save(Payment Payment) {
		return repository.save(Payment);
	}

}
