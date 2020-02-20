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
  private String cname;    //合同名称
  private String number;   //合同编号
  private java.sql.Date starttime;  //合同开始日期
  private java.sql.Date endtime;   //合同结束日期
  private String state;        //合同状态
  private String mark;         //合同标的
  private String type;          //合同类别
  private String typetwo;       //合同类别二级
  private String typethree;     //合同类别三级
  private String money;        //合同金额
  private String ifframe;           //是否为框架协议
  private String contraname;    //对方企业名称
  private String contranum;        //对方企业编码
  private String ifstock;       //是否有备料计划
  private String ifpurchase;      //是否有采购计划
  private String ifprequest;      //是否符合采购计划中采购单位要求
  private String purchasetype;     //采购方式
  private String ifppifurequest;   //采购方式是否符合采购计划批复要求
  private String ifstandard;      //采购过程是否规范
  private String ifequal;          //合同签订单价是否与采购结果单价一致
  private String remark;       //备注
  private Integer cproject;    //所属项目

}
