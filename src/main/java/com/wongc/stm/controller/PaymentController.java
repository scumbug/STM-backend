package com.wongc.stm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.wongc.stm.model.Payment;
import com.wongc.stm.service.PaymentService;

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
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService service;

        /*
     * Standard CRUD endpoints
     */

    @GetMapping("/")
    public List<Payment> getPayments() {
        return (List<Payment>) service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Payment> findById(@PathVariable Long id) {
        Optional<Payment> Payment = service.findById(id);
        if(Payment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Payment not found");
        }
        return Payment;
    }

    @PostMapping("/{id}")
    public Payment savePayment(@RequestBody Payment Payment) {
        Payment res = service.save(Payment);
        return res;
    }

    @PutMapping("/{id}")
    public Payment updatPayment(@RequestBody Payment Payment) {
        if(!service.existsById(Payment.getPaymentId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Payment not found");
        }
        Payment res = service.update(Payment);
        if(res == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Payment not found");
        }
        return res;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deletePaymentById(@PathVariable Long id){
        if(!service.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Payment not found");
        }
        service.deleteById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status","Payment deleted");
        return map;
    }

}
