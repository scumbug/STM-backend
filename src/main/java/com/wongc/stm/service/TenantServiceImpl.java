package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Tenant;
import com.wongc.stm.repository.TenantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImpl implements TenantService {
    @Autowired
    private TenantRepository repository;

    @Override
    public List<Tenant> findAll() {
        return (List<Tenant>) repository.findAll();
    }

    @Override
    public Optional<Tenant> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Tenant update(Tenant Tenant) {
        Optional<Tenant> res = repository.findById(Tenant.getTenantId());
        if(res.isEmpty())
            return null;
        Tenant tmp = res.get();
        return repository.save(tmp);
    }

    @Override
	public void deleteById(Long id) {
        repository.deleteById(id);
	}

    @Override
	public Tenant save(Tenant Tenant) {
		return repository.save(Tenant);
	}

}
