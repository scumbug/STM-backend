package com.wongc.stm.service;

import com.wongc.stm.model.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    List<Contact> findAll();
    Optional<Contact> findById(Long id);
    boolean existsById(Long id);
    Contact update(Contact Contact);
    void deleteById(Long id);
    Contact save(Contact Contact);

    List<Contact> findByUserId(Long id);
}
