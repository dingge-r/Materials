package com.example.materials.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

@Data
public class Project {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String blocname;         //集团名称
    private String companyname;       //企业名称
    private String projectbranchname; //项目部名称
    private String projectname;     //项目名称
    private String code;        //唯一主代码（10位，组织机构代码+报表类型码）
    private String retendernum;   //招标编号
    private String year;     //年度
    private Integer status;     //当前项目状态 0未完成，1已完成

}
