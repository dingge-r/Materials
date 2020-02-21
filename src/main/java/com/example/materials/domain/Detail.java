package com.example.materials.domain;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 支付详情
 */
@Data
@Table(name = "detail")
public class Detail {
  @Id
  @KeySql(useGeneratedKeys = true)
  private Integer id;
  private double settleprice;    //结算金额
  private double notaxsettle;  //不含税结算
  private double paymentprice; //付款金额
  private Integer time;    //第几次
  private Integer money;     //所属金额


}
