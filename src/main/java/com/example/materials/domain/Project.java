package com.example.materials.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

@Data
public class Project {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String blocname; //集团名称
    private String companyname; // 公司名称
    private String projectbranchname; //项目部门名称
    private String projectname; //项目名
    private String code;
    private String retendernum;
    private String year;
}
