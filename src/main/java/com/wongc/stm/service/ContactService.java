package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Contact;

public interface ContactService {
    public List<Contact> findAll();
    public Optional<Contact> findById(Long id);
    public boolean existsById(Long id);
    public Contact update(Contact Contact);
    public void deleteById(Long id);
    public Contact save(Contact Contact);

    public List<Contact> findByUserId(Long id);
}
