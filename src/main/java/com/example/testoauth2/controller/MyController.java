package com.example.testoauth2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class MyController {

    @RequestMapping(value = "/products")
    public String getProductName() {
        return "Honey";
    }
}
