package com.wongc.stm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @PostMapping
    public Object login(@RequestParam String username, @RequestParam String password) {
        // TODO: implement login logic
        return null;
    }
}
