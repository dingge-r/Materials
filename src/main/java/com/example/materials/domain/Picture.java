package com.example.materials.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 金额
 */
@Data
@Table(name = "picture")
public class Picture {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String image;  //图片地址
    private String comment;    //图片说明
    private  Integer project; //所属哪个项目

}
