package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Contact;
import com.wongc.stm.repository.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository repository;

    @Override
    public List<Contact> findAll() {
        return (List<Contact>) repository.findAll();
    }

    @Override
    public Optional<Contact> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Contact update(Contact Contact) {
        Optional<Contact> res = repository.findById(Contact.getContactId());
        if(!res.isPresent())
            return null;
        Contact tmp = res.get();
        return repository.save(tmp);
    }

    @Override
	public void deleteById(Long id) {
        repository.deleteById(id);
	}

    @Override
	public Contact save(Contact Contact) {
		return repository.save(Contact);
	}

    
    @Override
    // TODO: find contact by userId
    public List<Contact> findByUserId(Long id) {
        return repository.findByUserId(id);
    }

}
