package com.example.materials.domain;


import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 合同
 */
@Data
@Table(name = "contract")
public class Contract {
  @Id
  @KeySql(useGeneratedKeys = true)
  private Integer id;
  private String cname;
  private String number;
  private java.sql.Date starttime;
  private java.sql.Date endtime;
  private String state;
  private String mark;
  private String type;
  private String typetwo;
  private String typethree;
  private String money;
  private String ifframe;
  private String contraname;
  private String contranum;
  private String ifstock;
  private String ifpurchase;
  private String ifprequest;
  private String purchasetype;
  private String ifppifurequest;
  private String ifstandard;
  private String ifequal;
  private String remark;
  private Integer cproject;

}
