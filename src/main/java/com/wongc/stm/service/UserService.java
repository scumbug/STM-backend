package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.User;
import com.wongc.stm.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public User update(User user) {
        Optional<User> res = repository.findById(user.getUserId());
        if(res.isEmpty())
            return null;
        User tmp = res.get();
        return repository.save(tmp);
    }

	public void deleteById(Long id) {
        repository.deleteById(id);
	}

	public User save(User user) {
        // TODO: username dup check
		return repository.save(user);
	}
}
