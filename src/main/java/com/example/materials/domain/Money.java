package com.example.materials.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 金额
 */
@Data
@Table(name = "Money")
public class Money {
  @Id
  @KeySql(useGeneratedKeys = true)
  private Integer id;
  private String factoryname;  //厂家名称
  private String cargoname;    //货物名称
  private String contractprice;   //合同价格
  private String settleprice;       //结算金额
  private String paymentprice;    //付款金额
   private Integer project;     //所属项目

}
