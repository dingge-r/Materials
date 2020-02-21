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
    public ResponseEntity save(Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.save(project));
    }

    @PostMapping("update")
    public ResponseEntity update(Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.update(project));
    }

    @GetMapping("findByPage")
    public ResponseEntity findByPage(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "rows", defaultValue = "10") Integer rows){
        return ResponseEntity.ok(projectService.findByPage(page, rows));
    }

}
