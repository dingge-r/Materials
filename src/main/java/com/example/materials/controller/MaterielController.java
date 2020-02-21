package com.example.materials.controller;

import com.example.materials.service.MaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/materiel")
public class MaterielController {

    @Autowired
    private MaterielService materielService;
}
