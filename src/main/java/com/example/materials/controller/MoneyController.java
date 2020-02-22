package com.example.materials.controller;

import com.example.materials.domain.Money;
import com.example.materials.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/money")
public class MoneyController {

    @Autowired
    private MoneyService moneyService;

    //根据id查看
    @GetMapping("findById")
    public ResponseEntity findById(@RequestParam(name = "id") Integer id){
        return ResponseEntity.status(HttpStatus.CREATED).body(moneyService.findById(id));
    }

    //更新
    @PutMapping("update")
    public  ResponseEntity update(@RequestBody Money money){
        return ResponseEntity.status(HttpStatus.CREATED).body(moneyService.update(money));
    }

    //保存
    @PostMapping("save")
    public ResponseEntity save(@RequestBody Money money){
        return ResponseEntity.status(HttpStatus.CREATED).body(moneyService.save(money));
    }
}
