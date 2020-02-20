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
  private String factoryname; //工厂名称
  private String cargoname; //货物名称
  private String contractprice; //合约价格
  private String settleprice;  //单一价格
  private String paymentprice;  //支付金额
  private Integer paymentdatil;  //付款
  private Integer project;

}
