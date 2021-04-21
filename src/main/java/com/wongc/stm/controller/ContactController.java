package com.wongc.stm.controller;

import com.wongc.stm.model.Contact;
import com.wongc.stm.service.ContactServiceImpl;
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
@PreAuthorize("hasRole('SUPER') or hasRole('ADMIN') or hasRole('SALES')")
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    ContactServiceImpl service;

    /*
     * Standard CRUD endpoints
     */

    @GetMapping("/")
    public List<Contact> getUsers() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Contact> findById(@PathVariable Long id) {
        Optional<Contact> contact = service.findById(id);
        if(!contact.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        }
        return contact;
    }

    @PostMapping("/{id}")
    public Contact saveContact(@RequestBody Contact contact) {
        return service.save(contact);
    }

    @PutMapping("/{id}")
    public Contact updateContact(@RequestBody Contact contact){
        if(!service.existsById(contact.getContactId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact not found");
        }
        Contact res = service.update(contact);
        if(res == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact not found");
        }
        return res;
    }

    @DeleteMapping("/{id}")
    public Map<String,String> deleteContactById(@PathVariable Long id){
        if(!service.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact not found");
        }
        service.deleteById(id);
        Map<String, String> map = new HashMap<>();
        map.put("status","Contact deleted");
        return map;
    }

    @GetMapping("/users/{id}")
    public List<Contact> findByUserId(@PathVariable Long id) {
        return service.findByUserId(id);
    }
}
