package com.wongc.stm.controller;

import com.wongc.stm.model.Setting;
import com.wongc.stm.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('SUPER')")
public class SettingController {

    @Autowired
    SettingService settingService;

    @PostMapping("/update-template")
    public Setting updateEmailTemplate(@RequestBody Setting setting) {
        return settingService.update(setting);
    }

    @GetMapping("/email-template")
    public Setting getEmailTemplate() {
        return settingService.findByName("email_template");
    }

}
