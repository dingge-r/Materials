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
  private String settleprice;
  private String notaxsettle;
  private String paymentprice;
  private Integer time;
  private Integer money;


}
