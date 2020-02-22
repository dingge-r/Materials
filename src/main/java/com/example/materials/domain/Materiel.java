package com.example.materials.domain;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 物料
 */
@Data
@Table(name = "materiel")
public class Materiel {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String mname;  //物资名称
    private String type; //规格型号
    private String unit; //单位
    private Integer number; //数量
    private Double price;  //单价
    private Double sumprice;    //总价
    private String remark;   //备注
    private Integer mproject;  //所属项目
    private String date;
    private Integer status;     //当前项目状态 0未完成，1已完成


}
