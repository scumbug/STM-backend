package com.wongc.stm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Contact;
import com.wongc.stm.model.Tenant;
import com.wongc.stm.model.User;
import com.wongc.stm.model.enums.TenantStatus;
import com.wongc.stm.repository.ContactRepository;
import com.wongc.stm.repository.TenantRepository;

import com.wongc.stm.repository.UserRepository;
import com.wongc.stm.wrapper.TenantWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TenantServiceImpl implements TenantService {
    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Tenant> findAll() {
        return (List<Tenant>) tenantRepository.findAll();
    }

    @Override
    public Optional<Tenant> findById(Long id) {
        return tenantRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return tenantRepository.existsById(id);
    }

    @Override
    public Tenant update(Tenant Tenant) {
        Optional<Tenant> res = tenantRepository.findById(Tenant.getTenantId());
        if(!res.isPresent())
            return null;
        Tenant tmp = res.get();
        return tenantRepository.save(tmp);
    }

    @Override
	public void deleteById(Long id) {
        tenantRepository.deleteById(id);
	}

    @Override
    @Transactional
	public Tenant save(Tenant tenant, User user, List<Contact> contacts) {
        // add user to DB
        User savedUser = userRepository.save(user);
        // get user ID and append to contact and tenant object
        for (Contact contact : contacts) {
            contact.setUserId(savedUser.getUserId());
            contactRepository.save(contact);
        }
        tenant.setUserId(savedUser.getUserId());
        // save tenant and contact to DB
        return tenantRepository.save(tenant);
	}

    @Override
    public List<Tenant> findAllPotential() {
        return tenantRepository.findByTenantStatus(TenantStatus.POTENTIAL);
    }

    @Override
    public List<Tenant> findAllActive() {
        return tenantRepository.findByTenantStatus(TenantStatus.ACTIVE);
    }

    @Override
    public List<TenantWrapper> aggregate(TenantStatus tenantStatus) {
        List<Tenant> temp;
        if(tenantStatus == TenantStatus.ACTIVE) {
            temp = tenantRepository.findByTenantStatus(TenantStatus.ACTIVE);
        } else if(tenantStatus == TenantStatus.POTENTIAL) {
            temp = tenantRepository.findByTenantStatus(TenantStatus.POTENTIAL);
        } else {
            temp = (List<Tenant>) tenantRepository.findAll();
        }
        List<TenantWrapper> res = new ArrayList<>();
        for (Tenant tenant : temp) {
            Optional<User> user = userRepository.findById(tenant.getUserId());
            Optional<User> assignedAgent = userRepository.findById(tenant.getAssignedAgent());
            //List<Contact> contact = contactRepository.findByUserId(tenant.getUserId());
            TenantWrapper wrappedTenant = new TenantWrapper();
            wrappedTenant.setUser(user.get());
            wrappedTenant.setTenant(tenant);
            wrappedTenant.setAssignedAgent(assignedAgent.get());
            //wrappedTenant.setContact(contact);
            res.add(wrappedTenant);
        }
        return res;
    }

    @Override
    public Optional<Tenant> convert(Long id) {
        Optional<Tenant> conversion = tenantRepository.findById(id);
        conversion.get().setTenantId(id);
        conversion.get().setTenantStatus(TenantStatus.ACTIVE);
        tenantRepository.save(conversion.get());
        return conversion;
    }

}
