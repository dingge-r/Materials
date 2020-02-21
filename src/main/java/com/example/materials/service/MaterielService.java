package com.example.materials.service;

import com.example.materials.mapper.MaterielMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MaterielService {

    @Autowired
    private MaterielMapper materielMapper;
}
