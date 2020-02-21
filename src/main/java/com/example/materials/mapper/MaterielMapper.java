package com.example.materials.mapper;

import com.example.materials.domain.Materiel;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;


public interface MaterielMapper extends Mapper<Materiel> {

    @Update("update materiel set status = #{status}, date = #{date} where id = #{id}")
    void updateStatus(Integer id, Integer status, String date);

    //更新为未完成以后，改变project的status
    @Update("update project set status = 0 where id = #{project}")
    void updateProjectStatus(Integer project);
}
