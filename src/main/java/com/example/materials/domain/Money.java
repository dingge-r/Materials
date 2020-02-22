package com.example.materials.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 金额
 */
@Data
@Table(name = "money")
public class Money {
  @Id
  @KeySql(useGeneratedKeys = true)
  private Integer id;
  private String factoryname;  //厂家名称
  private String cargoname;    //货物名称
  private double contractprice;   //合同价格
  private double settleprice;       //结算金额
  private double paymentprice;    //付款金额
  private Integer project;     //所属项目
  private Integer status;     //当前项目状态 0未完成，1已完成

  @Transient
  private List<Detail> detailList;

}
