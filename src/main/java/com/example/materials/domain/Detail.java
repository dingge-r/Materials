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
  private String settleprice;    //结算金额
  private String notaxsettle;  //不含税结算
  private String paymentprice; //付款金额
  private Integer time;    //第几次
  private Integer money;     //所属金额


}
