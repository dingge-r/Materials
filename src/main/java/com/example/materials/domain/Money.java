package com.example.materials.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 金额
 */
@Data
@Table(name = "project")
public class Money {
  @Id
  @KeySql(useGeneratedKeys = true)
  private Integer id;
  private String factoryname;
  private String cargoname;
  private String contractprice;
  private String settleprice;
  private String paymentprice;
  private Integer paymentdatil;
  private Integer project;

}
