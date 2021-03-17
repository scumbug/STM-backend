package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.User;

public interface UserService {

    public List<User> findAll();
    public Optional<User> findById(Long id);
    public boolean existsById(Long id);
    public User update(User user);
    public void deleteById(Long id);
    public User save(User user);

}
