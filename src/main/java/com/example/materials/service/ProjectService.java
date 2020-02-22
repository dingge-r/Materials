package com.example.materials.service;

import com.example.materials.domain.Project;
import com.example.materials.mapper.ProjectMapper;
import com.example.materials.utils.DateUtils;
import com.example.materials.utils.JsonData;
import com.example.materials.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    //根据项目名称查id
    public Integer findIdByName(String name) {
        return projectMapper.findIdByName(name);
    }

    public JsonData save(Project project) {
        if (!checkProjectname(project.getProjectname())) {
            return JsonData.buildError("该项目名已存在，添加失败");
        }
        project.setCreateTime(DateUtils.dateByString());
        int i = projectMapper.insertSelective(project);
        if (i != 1) {
            return JsonData.buildError("保存失败");
        }
        return JsonData.buildSuccess("保存成功");
    }

    public Project findById(Integer id) {
        return projectMapper.selectByPrimaryKey(id);
    }

    public JsonData update(Project project) {
        if (!checkProjectname(project.getProjectname())) {
            return JsonData.buildError("该项目名已存在，添加失败");
        }
        project.setStatus(null);
        project.setCreateTime(DateUtils.dateByString());
        int i = projectMapper.updateByPrimaryKeySelective(project);
        if (i != 1) {
            return JsonData.buildError("保存失败");
        }
        return JsonData.buildSuccess("保存成功");
    }

    //判断所有的Materiel状态是否为1
    public int StatusByMateriel(int id) {
        int[] a = projectMapper.findStatusByMateriel(id);
        for (int i : a) {
            if (i != 1) {
                return 0;
            }
        }
        return 1;
    }

    public int StatusByContract(int id) {
        Object b = projectMapper.findStatusByContract(id);
        if (b == null || "0".equals(b)) {
            return 0;
        }
        return 1;
    }

    public int StatusByMoney(int id) {
        Object b = projectMapper.findStatusByMoney(id);
        if (b == null || "0".equals(b)) {
            return 0;
        }
        return 1;
    }

    //已完成的项目
    public PageResult<Project> findByPage(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        Example example = new Example(Project.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 1);
        example.setOrderByClause("create_time desc");
        Page<Project> projectPage1 = (Page<Project>) projectMapper.selectByExample(example);
        return new PageResult<>(projectPage1.getTotal(), projectPage1.getPages(), projectPage1.getResult());
    }

    //校验用户名是否存在
    public boolean checkProjectname(String projectname) {
        Example example = new Example(Project.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectname", projectname);
        Project project = projectMapper.selectOneByExample(example);
        return project == null;
    }

    //所有未完成项目
    public JsonData findUndone() {
        Example example = new Example(Project.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 0);
        List<Project> projectList = projectMapper.selectByExample(example);
        for (Project project : projectList) {
            if (project.getStatus() != 1) {
                //通过查询分支的状态来判断整个项目是否完成
                int a = StatusByMateriel(project.getId());
                int b = StatusByContract(project.getId());
                int c = StatusByMoney(project.getId());
                //如果分支都已完成，项目改变状态
                if (a == 1 && b == 1 && c == 1) {
                    project.setStatus(1);
                    projectMapper.updateStatus(project.getId());
                }

            }
        }
        example.setOrderByClause("create_time desc");
        List<Project> projectList1 = projectMapper.selectByExample(example);
        return JsonData.buildSuccess(projectList1, "");
    }

    //搜索
    public JsonData findByLike(String projectName) {
        Example example = new Example(Project.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("projectname", "%" + projectName + "%");
        example.setOrderByClause("create_time desc");
        List<Project> projectList = projectMapper.selectByExample(example);
        return JsonData.buildSuccess(projectList, "");
    }
}
