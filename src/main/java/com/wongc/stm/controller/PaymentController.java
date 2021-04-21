package com.wongc.stm.controller;

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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@PreAuthorize("hasRole('SUPER') or hasRole('SALES') or hasRole('TENANT')")
public class PaymentController {
    @Autowired
    PaymentServiceImpl service;

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
            return service.findAll();
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
                               @RequestParam("form") String payment) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Payment res = mapper.readValue(payment,Payment.class);

        // Save file to local
        Path root = Paths.get("uploads");
        Files.copy(paymentProof.getInputStream(),root.resolve(paymentProof.getOriginalFilename()));
        res.setPaymentProof(paymentProof.getOriginalFilename());

        //byte[] bytes = paymentProof.getBytes();
        //Blob image = new SerialBlob(bytes);
        //res.setPaymentProof(image);
        return service.update(res);
    }

    @PostMapping("")
    public Payment savePayment(@RequestBody Payment payment) {
        if(!service.existsById(payment.getPaymentId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Payment not found");
        }
        Payment res = service.update(payment);
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
        Map<String, Object> map = new HashMap<>();
        map.put("status","Payment deleted");
        return map;
    }

}
