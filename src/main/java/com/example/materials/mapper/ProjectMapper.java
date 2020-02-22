package com.example.materials.mapper;

import com.example.materials.domain.Project;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface ProjectMapper extends Mapper<Project> {

    @Select("select id from project where projectname = #{projectname}")
    Integer findIdByName(String projectname);

    @Select("select status from materiel where mproject = #{projectId}")
    int[] findStatusByMateriel(Integer projectId);

    @Select("select cstatus from contract where cproject = #{projectId}")
    Integer findStatusByContract(Integer projectId);

    @Select("select status from money where project = #{projectId}")
    Integer findStatusByMoney(Integer projectId);

    @Update("update project set status = 1 where id = #{id}")
    void updateStatus(Integer id);
}
