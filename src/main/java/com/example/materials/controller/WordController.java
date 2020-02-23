package com.example.materials.controller;

import com.example.materials.domain.Word;
import com.example.materials.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/word")
public class WordController {
    @Autowired
    private WordService wordService;

    //上传文件
    @PostMapping("uploadWord")
    public ResponseEntity uploadWord(@RequestParam(name = "file")MultipartFile file){
        return ResponseEntity.status(HttpStatus.CREATED).body(wordService.uploadWord(file));
    }

    @PostMapping("save")
    public ResponseEntity save(@RequestBody Word word){
        return ResponseEntity.status(HttpStatus.CREATED).body(wordService.save(word));
    }

    @PostMapping("update")
    public ResponseEntity update(@RequestBody Word word){
        return ResponseEntity.status(HttpStatus.CREATED).body(wordService.update(word));
    }

    @GetMapping("delete")
    public ResponseEntity delete(@RequestParam(name = "id") Integer id){
        return ResponseEntity.ok(wordService.delete(id));
    }
}
