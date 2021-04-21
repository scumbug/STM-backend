package com.wongc.stm.service;

import com.wongc.stm.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    Optional<User> findById(Long id);
    boolean existsById(Long id);
    User update(User user);
    void deleteById(Long id);
    User save(User user);
    List<User> findAllAgents();

}
