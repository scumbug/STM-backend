package com.wongc.stm.controller;

import com.wongc.stm.model.User;
import com.wongc.stm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('SUPER') or hasRole('SALES')")

public class UserController {

    @Autowired
    UserServiceImpl service;

    /*
     * Standard CRUD endpoints
     */

    @GetMapping("")
    public List<User> getUsers() {
        return service.findAll();
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
        return service.save(user);
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
        Map<String, Object> map = new HashMap<>();
        map.put("status", "User deleted");
        return map;
    }

    // GET all sales agent
    @GetMapping("/agents")
    public List<User> getAllAgents() {
        return service.findAllAgents();
    }

}
