package com.example.materials.mapper;

import com.example.materials.domain.Project;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface ProjectMapper extends Mapper<Project> {

    @Select("select projectname from project where id = #{id}")
    String findNameById(Integer id);
}
