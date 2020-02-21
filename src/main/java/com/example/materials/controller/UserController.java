package com.example.materials.controller;

import com.example.materials.domain.User;
import com.example.materials.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.login(user));
    }

    @PostMapping("save")
    public ResponseEntity save(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @GetMapping("delete")
    public ResponseEntity delete(@RequestParam(name = "id") Integer id){
        return ResponseEntity.ok(userService.delete(id));
    }

    @GetMapping("findByPage")
    public ResponseEntity findByPage(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "rows", defaultValue = "10") Integer rows){
        return ResponseEntity.ok(userService.findByPage(page, rows));
    }

}
