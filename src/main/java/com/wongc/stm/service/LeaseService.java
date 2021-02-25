package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Lease;
import com.wongc.stm.repository.LeaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaseService {
    @Autowired
    private LeaseRepository repository;

    public List<Lease> findAll() {
        return (List<Lease>) repository.findAll();
    }

    public Optional<Lease> findById(Long id) {
        return repository.findById(id);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public Lease update(Lease Lease) {
        Optional<Lease> res = repository.findById(Lease.getLeaseId());
        if(res.isEmpty())
            return null;
        Lease tmp = res.get();
        return repository.save(tmp);
    }

	public void deleteById(Long id) {
        repository.deleteById(id);
	}

	public Lease save(Lease Lease) {
		return repository.save(Lease);
	}
}
