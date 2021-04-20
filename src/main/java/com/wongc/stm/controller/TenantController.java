package com.wongc.stm.controller;

import com.wongc.stm.dto.TenantDTO;
import com.wongc.stm.model.Property;
import com.wongc.stm.model.Tenant;
import com.wongc.stm.model.enums.TenantStatus;
import com.wongc.stm.service.TenantServiceImpl;
import com.wongc.stm.wrapper.TenantWrapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tenants")
@PreAuthorize("hasRole('SUPER') or hasRole('SALES')")
public class TenantController {
    @Autowired
    private TenantServiceImpl service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public List<Tenant> getTenants(@RequestParam(required = true) TenantStatus tenantStatus) {
        if(tenantStatus == TenantStatus.POTENTIAL) {
            return (List<Tenant>) service.findAllPotential();
        } else if(tenantStatus == TenantStatus.ACTIVE) {
            return (List<Tenant>) service.findAllActive();
        } else if (tenantStatus == TenantStatus.PENDING) {
            return (List<Tenant>) service.findAllPending();
        }
        else {
            return (List<Tenant>) service.findAll();
        }
    }

    @PostMapping("")
    public Tenant addTenant(@RequestBody TenantWrapper payload) {
        Tenant res = service.save(payload.getTenant(),payload.getUser(),payload.getContact());
        return res;
    }

    @GetMapping("/aggregate")
    public List<TenantWrapper> getAggregate(@RequestParam(required = false) TenantStatus tenantStatus) {
            return service.aggregate(tenantStatus);
    }

    @GetMapping("/convert/{id}")
    public Optional<Tenant> convertTenant(@PathVariable Long id) {
        return service.convert(id);
    }

    @GetMapping("/{id}")
    public Optional<Tenant> getTenant(@PathVariable Long id) {
        Optional<Tenant> tenant = service.findById(id);
        if(!tenant.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Property not found");
        }
        return tenant;
    }

    @PreAuthorize("hasRole('SUPER') or hasRole('TENANT')")
    @GetMapping("/users/{id}")
    public Tenant findTenantByUserId(@PathVariable Long id) {
        return service.findByUserId(id);
    }
}
