package com.wongc.stm.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wongc.stm.model.Payment;
import com.wongc.stm.service.PaymentServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.rowset.serial.SerialBlob;

@RestController
@RequestMapping("/api/payments")
@PreAuthorize("hasRole('SUPER') or hasRole('SALES')")
public class PaymentController {
    @Autowired
    private PaymentServiceImpl service;

        /*
     * Standard CRUD endpoints
     */

    @GetMapping("")
    public List<Payment> getPayments(@RequestParam(required = false, name = "tenantId") Long tenantId,
                                     @RequestParam(required = false, name = "unitId") Long unitId)
    {
        if(tenantId != null && unitId != null) {
            return service.findPaymentsForTenant(tenantId,unitId);
        } else {
            return (List<Payment>) service.findAll();
        }
    }

    @GetMapping("/{id}")
    public Optional<Payment> findById(@PathVariable Long id) {
        Optional<Payment> Payment = service.findById(id);
        if(!Payment.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Payment not found");
        }
        return Payment;
    }

    @PutMapping(value = "",consumes = { MediaType.APPLICATION_OCTET_STREAM_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public Payment updatePayment(@RequestPart("paymentProof") MultipartFile paymentProof,
                               @RequestParam("form") String payment) throws IOException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        Payment res = mapper.readValue(payment,Payment.class);
        //byte[] bytes = paymentProof.getBytes();
        //Blob image = new SerialBlob(bytes);
        //res.setPaymentProof(image);
        return service.save(res);
    }

    @PostMapping("")
    public Payment savePayment(@RequestBody Payment Payment) {
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
