package com.wongc.stm.service;

import com.wongc.stm.dto.UnitRent;
import com.wongc.stm.model.Contact;
import com.wongc.stm.model.Lease;
import com.wongc.stm.model.Tenant;
import com.wongc.stm.model.enums.TenantStatus;
import com.wongc.stm.repository.ContactRepository;
import com.wongc.stm.repository.LeaseRepository;
import com.wongc.stm.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaseServiceImpl implements LeaseService {
    @Autowired
    LeaseRepository repository;

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    ContactRepository contactRepository;

    @Override
    public List<Lease> findAll() {
        return (List<Lease>) repository.findAll();
    }

    @Override
    public Optional<Lease> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Lease update(Lease Lease) {
        Optional<Lease> res = repository.findById(Lease.getLeaseId());
        if(!res.isPresent())
            return null;
        Lease tmp = res.get();
        return repository.save(tmp);
    }

    @Override
	public void deleteById(Long id) {
        repository.deleteById(id);
	}

    @Override
	public Lease save(Lease lease) {
        // add lease to DB
		return repository.save(lease);
	}

	@Override
    public Lease convertTenant(Lease lease, List<Contact> contacts) {

        // change tenant status to PENDING
        Tenant tenant = tenantRepository.findById(lease.getTenantId()).get();
        tenant.setTenantStatus(TenantStatus.PENDING);
        tenant.setLeasedUnit(lease.getUnitId());
        tenantRepository.save(tenant);

        // add contact
        for (Contact contact : contacts) {
            contactRepository.save(contact);
        }

        //add lease to DB
        return repository.save(lease);
    }

    @Override
    public Optional<Lease> findByUnitId(Long id) {
        return repository.findByUnitId(id);
    }

    @Override
    public List<UnitRent> getHistoricalRentByUnitId(Long id) {

        return repository.findHistoricRentByUnitId(id);
    }
}
