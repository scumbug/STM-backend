package com.wongc.stm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @RequestMapping("/users")
    @ResponseBody
    public String getUsers() {
        return "this is get users";
    }
}
