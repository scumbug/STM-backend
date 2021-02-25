package com.wongc.stm.repository;

import java.util.List;

import com.wongc.stm.model.Contact;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {
    List<Contact> findByUserId (Long userId);
}
