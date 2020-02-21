package com.example.materials.service;

import com.example.materials.mapper.MoneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MoneyService {

    @Autowired
    private MoneyMapper moneyMapper;
}
