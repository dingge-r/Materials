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

    //多条件查询
    @GetMapping("findByLike")
    public ResponseEntity findByLike(@RequestParam(name = "name", required = false) String name, //物料名称
                                     @RequestParam(name = "projectName", required = false) String projectName, //项目名
                                     @RequestParam(name = "remark", required = false) String remark, //备注
                                     @RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "rows", defaultValue = "10") Integer rows){
        return ResponseEntity.ok(materielService.findByLike(name, projectName, remark, page, rows));
    }

    @GetMapping("findById")
    public ResponseEntity findById(@RequestParam(name = "id") Integer id){
        return ResponseEntity.ok(materielService.findById(id));
    }

    @PostMapping("update")
    public ResponseEntity update(@RequestBody Materiel materiel){
        return ResponseEntity.status(HttpStatus.CREATED).body(materielService.update(materiel));
    }

    @GetMapping("findByProjectId")
    public ResponseEntity findByProjectId(@RequestParam(name = "projectId") Integer projectId){
        return ResponseEntity.ok(materielService.findByProjectId(projectId));
    }

    @GetMapping("delete")
    public ResponseEntity delete(@RequestParam(name = "id") Integer id){
        return ResponseEntity.ok(materielService.delete(id));
    }

    //添加到已完成
    @GetMapping("updateStatus")
    public ResponseEntity updateStatus(@RequestParam(name = "projectId") Integer projectId){
        return ResponseEntity.ok(materielService.updateStatus(projectId));
    }
}
