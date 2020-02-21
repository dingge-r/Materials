package com.example.materials.service;

import com.example.materials.domain.Materiel;
import com.example.materials.mapper.MaterielMapper;
import com.example.materials.utils.DateUtils;
import com.example.materials.utils.JsonData;
import com.example.materials.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class MaterielService {

    @Autowired
    private MaterielMapper materielMapper;

    @Autowired
    private ProjectService projectService;

    public JsonData save(Materiel materiel) {
        materiel.setSumprice(materiel.getPrice() * materiel.getNumber());
        int i = materielMapper.insertSelective(materiel);
        if (i != 1){
            return JsonData.buildError("保存失败");
        }
        return JsonData.buildSuccess("保存成功");
    }

    public Materiel findById(Integer id){
        Materiel materiel = materielMapper.selectByPrimaryKey(id);
        materiel.setProjectName(projectService.findNameById(materiel.getMproject()));
        return materiel;
    }

    public PageResult<Materiel> findByPage(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        Page<Materiel> userPage = (Page<Materiel>) materielMapper.selectAll();
        for (Materiel materiel : userPage) {
            materiel.setProjectName(projectService.findNameById(materiel.getMproject()));
        }
        return new PageResult<>(userPage.getTotal(), userPage.getPages(), userPage.getResult());
    }

    public JsonData update(Materiel materiel) {
        /*Materiel materiel1 = findById(materiel.getId());
        if (materiel1.getStatus() == 1){
            return JsonData.buildError("已完成项目，不允许再次修改");
        }*/
        materiel.setDate(DateUtils.dateByString());
        int i = materielMapper.updateByPrimaryKeySelective(materiel);
        if (i != 1){
            return JsonData.buildError("更新失败");
        }
        return JsonData.buildSuccess("更新成功");
    }

    /*public JsonData updateStatus(Integer id, Integer status) {
        if (status == 0 || status == 1){
            materielMapper.updateStatus(id, status , DateUtils.dateByString());
            if (status == 0){
                //更新为未完成以后，改变project的status
                Materiel materiel = materielMapper.selectByPrimaryKey(id);
                materielMapper.updateProjectStatus(materiel.getMproject());
            }
            return JsonData.buildSuccess("成功");
        }
        return JsonData.buildError("数据有问题");
    }*/
}
