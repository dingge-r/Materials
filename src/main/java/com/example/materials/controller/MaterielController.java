package com.example.materials.controller;

import com.example.materials.domain.Materiel;
import com.example.materials.service.MaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/materiel")
public class MaterielController {

    @Autowired
    private MaterielService materielService;

    @PostMapping("save")
    public ResponseEntity save(@RequestBody Materiel materiel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materielService.save(materiel));
    }

    @GetMapping("findByPage")
    public ResponseEntity findByPage(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "rows", defaultValue = "10") Integer rows){
        return ResponseEntity.ok(materielService.findByPage(page, rows));
    }

    @GetMapping("findById")
    public ResponseEntity findById(@RequestParam(name = "id") Integer id){
        return ResponseEntity.ok(materielService.findById(id));
    }

    @PostMapping("update")
    public ResponseEntity update(@RequestBody Materiel materiel){
        return ResponseEntity.status(HttpStatus.CREATED).body(materielService.update(materiel));
    }

    //更新状态
    @GetMapping("updateStatus")
    public ResponseEntity updateStatus(@RequestParam(name = "id") Integer id,
                                       @RequestParam(name = "status") Integer status){
        return ResponseEntity.ok(materielService.updateStatus(id, status));
    }
}
