package com.example.ribbonserverdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/users")
    public String users(HttpServletRequest request) {
        return "users: "+request.getRequestURL();
    }
}
