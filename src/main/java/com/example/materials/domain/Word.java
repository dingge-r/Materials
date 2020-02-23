package com.example.materials.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

@Data
public class Word {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String wordName;
    private String explain;
    private Integer projectId;
}
