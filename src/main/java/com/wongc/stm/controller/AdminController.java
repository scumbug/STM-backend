package com.wongc.stm.controller;

import com.wongc.stm.dto.EmailTemplate;
import com.wongc.stm.model.Admin;
import com.wongc.stm.model.Unit;
import com.wongc.stm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('SUPER')")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/update-template")
    public Admin updateEmailTemplate(@RequestBody Admin admin) {
        return adminService.update(admin);
    }

    @GetMapping("/email-template")
    public Admin getEmailTemplate() {
        return adminService.findByName("email_template");
    }

}
