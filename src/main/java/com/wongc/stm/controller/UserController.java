package com.wongc.stm.controller;

import java.util.List;

import com.wongc.stm.model.User;
import com.wongc.stm.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/users")
    @ResponseBody
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }
}
