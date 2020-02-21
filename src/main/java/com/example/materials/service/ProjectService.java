package com.example.materials.service;

import com.example.materials.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

     //根据id查项目名称
    public String findNameById(Integer id){
        return projectMapper.findNameById(id);
    }
}
