package com.wongc.stm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.wongc.stm.model.User;
import com.wongc.stm.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl service;

    /*
     * Standard CRUD endpoints
     */

    @GetMapping("")
    public List<User> getUsers() {
        return (List<User>) service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        Optional<User> user = service.findById(id);
        if (!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return user;
    }

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        User res = service.save(user);
        return res;
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user) {
        if (!service.existsById(user.getUserId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        User res = service.update(user);
        if (res == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return res;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUserById(@PathVariable Long id) {
        if (!service.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        service.deleteById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "User deleted");
        return map;
    }

    // GET all sales agent
    @GetMapping("/agents")
    public List<User> getAllAgents() {
        return (List<User>) service.findAllAgents();
    }

}
