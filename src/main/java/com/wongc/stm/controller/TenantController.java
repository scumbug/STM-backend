package com.wongc.stm.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wongc.stm.dto.TenantDTO;
import com.wongc.stm.model.Contact;
import com.wongc.stm.model.Tenant;
import com.wongc.stm.model.User;
import com.wongc.stm.model.enums.TenantStatus;
import com.wongc.stm.service.TenantServiceImpl;
import com.wongc.stm.wrapper.TenantWrapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tenants")
//@PreAuthorize("hasRole('TENANT')")
public class TenantController {
    @Autowired
    private TenantServiceImpl service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public List<Tenant> getTenants(@RequestParam(required = false) TenantStatus tenantStatus) {
        if(tenantStatus == TenantStatus.POTENTIAL) {
            return (List<Tenant>) service.findAllPotential();
        } else if(tenantStatus == TenantStatus.ACTIVE) {
            return (List<Tenant>) service.findAllActive();
        } else {
            return (List<Tenant>) service.findAll();
        }
    }

    @PostMapping("")
    public Tenant addTenant(@RequestBody TenantWrapper payload) {
        Tenant res = service.save(payload.getTenant(),payload.getUser(),payload.getContact());
        return res;
    }

    @GetMapping("/aggregate")
    public List<TenantWrapper> getAggregate() {
        return service.aggregate();
    }

    @GetMapping("/dto")
    public List<TenantDTO> tryDTO() {
        List<Tenant> tenants = service.findAll();
        return tenants.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    private TenantDTO convertToDTO(Tenant tenant) {
        TenantDTO tenantDTO = modelMapper.map(tenant,TenantDTO.class);
        return tenantDTO;
    }
}
