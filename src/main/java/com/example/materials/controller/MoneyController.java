package com.example.materials.controller;

import com.example.materials.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/money")
public class MoneyController {

    @Autowired
    private MoneyService moneyService;
}
