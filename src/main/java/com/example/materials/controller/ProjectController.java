package com.example.materials.controller;

import com.example.materials.domain.Project;
import com.example.materials.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("save")
    public ResponseEntity save(@RequestBody Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.save(project));
    }

    @GetMapping("findById")
    public ResponseEntity findById(@RequestParam(name = "id") Integer id){
        return ResponseEntity.ok(projectService.findById(id));
    }

    @PostMapping("update")
    public ResponseEntity update(@RequestBody Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.update(project));
    }

    //所有已完成的项目
    @GetMapping("findByPage")
    public ResponseEntity findByPage(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "rows", defaultValue = "30") Integer rows){
        return ResponseEntity.ok(projectService.findByPage(page, rows));
    }
    //未完成的项目
    @GetMapping("findUndone")
    public ResponseEntity findUndone(){
        return ResponseEntity.ok(projectService.findUndone());
    }

    //根据项目名称搜索
    @GetMapping("findByLike")
    public ResponseEntity findByLike(@RequestParam(name = "projectName") String projectName){
        return ResponseEntity.ok(projectService.findByLike(projectName));
    }

}
