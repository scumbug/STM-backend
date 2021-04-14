package com.wongc.stm.service;

import com.wongc.stm.model.Admin;
import com.wongc.stm.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Override
    public Admin update(Admin admin) {
        System.out.println(admin);
        return adminRepository.save(admin);
    }

    @Override
    public Admin findByName(String name) {
        return adminRepository.findByName(name);
    }
}
