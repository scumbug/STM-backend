package com.wongc.stm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping(value = "/")
    @ResponseBody
    public String listThings() {
        return "Hello World";
    }
}
