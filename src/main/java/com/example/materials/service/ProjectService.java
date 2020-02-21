package com.example.materials.service;

import com.example.materials.domain.Project;
import com.example.materials.mapper.ProjectMapper;
import com.example.materials.utils.JsonData;
import com.example.materials.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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

    public JsonData save(Project project) {
        int i = projectMapper.insertSelective(project);
        if (i != 1){
            return JsonData.buildError("保存失败");
        }
        return JsonData.buildSuccess("保存成功");
    }

    public JsonData update(Project project) {
        project.setStatus(null);
        int i = projectMapper.updateByPrimaryKeySelective(project);
        if (i != 1){
            return JsonData.buildError("保存失败");
        }
        return JsonData.buildSuccess("保存成功");
    }

    public PageResult<Project> findByPage(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        Page<Project> projectPage = (Page<Project>) projectMapper.selectAll();
        for (Project project : projectPage) {
            if (project.getStatus() != 1){
                //通过查询分支的状态来判断整个项目是否完成
                int a = projectMapper.findStatusByMateriel(project.getId());
                int b = projectMapper.findStatusByContract(project.getId());
                int c = projectMapper.findStatusByMoney(project.getId());
                //如果分支都已完成，项目改变状态
                if (a == 1 && b == 1 && c == 1){
                    project.setStatus(1);
                    projectMapper.updateStatus(project.getId());
                }
            }
        }
        return new PageResult<>(projectPage.getTotal(), projectPage.getPages(), projectPage.getResult());
    }
}
