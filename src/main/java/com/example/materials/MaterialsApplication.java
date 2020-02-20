package com.example.materials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.materials.mapper")
public class MaterialsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaterialsApplication.class, args);
    }

}
