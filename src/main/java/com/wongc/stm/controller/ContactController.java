package com.wongc.stm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.wongc.stm.model.Contact;
import com.wongc.stm.service.ContactServiceImpl;

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
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private ContactServiceImpl service;

    /*
     * Standard CRUD endpoints
     */

    @GetMapping("/")
    public List<Contact> getUsers() {
        return (List<Contact>) service.findAll();
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
        Contact res = service.save(contact);
        return res;
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
    public Map<String,Object> deleteContactbyId(@PathVariable Long id){
        if(!service.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact not found");
        }
        service.deleteById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status","Contact deleted");
        return map;
    }

    @GetMapping("/users/{id}")
    public List<Contact> findByUserId(@PathVariable Long id) {
        return service.findByUserId(id);
    }
}
