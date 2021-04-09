package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Payment;
import com.wongc.stm.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository repository;

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
    public Payment update(Payment Payment) {
        Optional<Payment> res = repository.findById(Payment.getPaymentId());
        if(!res.isPresent())
            return null;
        Payment tmp = res.get();
        return repository.save(tmp);
    }

    @Override
	public void deleteById(Long id) {
        repository.deleteById(id);
	}

    @Override
	public Payment save(Payment Payment) {
		return repository.save(Payment);
	}

}
