package com.example.materials.controller;

import com.example.materials.domain.Picture;
import com.example.materials.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/picture")
public class PictureController {

    @Autowired
    private PictureService pictureService;


    //上传图片，返回图片存放路径
    @PostMapping("uploadImage")
    public ResponseEntity save(@RequestParam(name = "img") MultipartFile img) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pictureService.uploadImage(img));
    }

    //更新
    @PutMapping("update")
    public ResponseEntity update(@RequestBody Picture picture){
        return ResponseEntity.status(HttpStatus.CREATED).body(pictureService.update(picture));
    }

    //保存
    @PostMapping("save")
    public ResponseEntity save(@RequestBody Picture picture){
        return  ResponseEntity.status(HttpStatus.CREATED).body(pictureService.save(picture));
    }

    //删除
    @DeleteMapping("delete")
    public ResponseEntity delete(@RequestParam(name = "id") Integer id){
        return  ResponseEntity.status(HttpStatus.CREATED).body(pictureService.delete(id));
    }


}

