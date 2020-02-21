package com.example.materials.controller;

import com.example.materials.domain.Contract;
import com.example.materials.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    //查看所有
    @GetMapping("findAll")
    public  ResponseEntity  findAll(){
        return ResponseEntity.status(HttpStatus.CREATED).body(contractService.findAll());
    }

    //通过id查看
    @GetMapping("findById")
    public  ResponseEntity  findById(@RequestParam(name = "id") Integer id){
        return ResponseEntity.status(HttpStatus.CREATED).body(contractService.findByid(id));
    }

    //更新
    @PutMapping("update")
    public ResponseEntity update(@RequestBody Contract contract) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contractService.update(contract));
    }

    //删除
    @DeleteMapping("delete")
    public ResponseEntity delete(@RequestParam(name = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contractService.delete(id));
    }

    //保存
    @PostMapping("save")
    public ResponseEntity save(@RequestBody Contract contract) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contractService.save(contract));
    }
}
