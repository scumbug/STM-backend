package com.wongc.stm.service;

import com.wongc.stm.model.Admin;
import org.springframework.stereotype.Component;

public interface AdminService {

    public Admin update(Admin admin);

    public Admin findByName(String name);
}
