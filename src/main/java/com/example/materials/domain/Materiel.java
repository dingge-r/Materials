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
  private String mname;
  private String type;
  private String unit;
  private Integer number;
  private String price;
  private String sumprice;
  private String remark;
  private Integer mproject;

}
