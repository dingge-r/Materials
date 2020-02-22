package com.example.materials.controller;

import com.example.materials.domain.Detail;
import com.example.materials.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/detail")
public class DetailController {

    @Autowired
    private DetailService detailService;

    //保存
    @PostMapping("save")
    public ResponseEntity save(@RequestBody Detail detail){
        return ResponseEntity.status(HttpStatus.CREATED).body(detailService.save(detail));
    }

    //更新
    @PutMapping("update")
    public  ResponseEntity update(@RequestBody Detail detail){
        return  ResponseEntity.status(HttpStatus.CREATED).body(detailService.update(detail));
    }
}
