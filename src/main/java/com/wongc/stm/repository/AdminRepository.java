package com.wongc.stm.repository;

import com.wongc.stm.model.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdminRepository extends PagingAndSortingRepository<Admin, Long> {
    Admin findByName(String name);
}
