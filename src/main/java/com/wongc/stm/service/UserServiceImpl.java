package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.User;
import com.wongc.stm.model.enums.UserType;
import com.wongc.stm.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public User update(User user) {
        Optional<User> res = repository.findById(user.getUserId());
        if (!res.isPresent())
            return null;
        User tmp = res.get();
        return repository.save(tmp);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User save(User user) {
        // TODO: username dup check
        return repository.save(user);
    }
    @Override
    public List<User> findAllAgents() {
        return repository.findByType(UserType.SALES);
    }
}
