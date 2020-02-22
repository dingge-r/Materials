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
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
@Transactional
public class MaterielService {

    @Autowired
    private MaterielMapper materielMapper;

    @Autowired
    private ProjectService projectService;

    public JsonData save(Materiel materiel) {
        double sumprice = materiel.getPrice() * materiel.getNumber();//计算总价
        if (sumprice != materiel.getSumprice()){
            return JsonData.buildError("请校验总价是否正确");
        }
        materiel.setDate(DateUtils.dateByString()); //设置日期
        int i = materielMapper.insertSelective(materiel);
        if (i != 1){
            return JsonData.buildError("保存失败");
        }
        return JsonData.buildSuccess("保存成功");
    }

    public Materiel findById(Integer id){
        return materielMapper.selectByPrimaryKey(id);
    }

    //多条件搜索
    public PageResult<Materiel> findByLike(String name,String projectName,String remark, Integer page, Integer rows) {
        Example example = new Example(Materiel.class);
        Example.Criteria criteria = example.createCriteria();
        if (name != null){
            criteria.andLike("mname", "%" + name + "%");
        }
        if (projectName != null) {
            Object projectId = projectService.findIdByName(projectName);
            if (projectId == null){
                return new PageResult<>();
            }
            criteria.andEqualTo("mproject", projectId);
        }
        if (remark != null){
            criteria.andLike("remark", "%" + remark + "%");
        }
        PageHelper.startPage(page, rows);
        Page<Materiel> userPage = (Page<Materiel>) materielMapper.selectByExample(example);
        return new PageResult<>(userPage.getTotal(), userPage.getPages(), userPage.getResult());
    }

    public JsonData update(Materiel materiel) {
        materiel.setDate(DateUtils.dateByString());
        int i = materielMapper.updateByPrimaryKeySelective(materiel);
        if (i != 1){
            return JsonData.buildError("更新失败");
        }
        return JsonData.buildSuccess("更新成功");
    }

    //通过ProjectId查物料
    public JsonData findByProjectId(Integer projectId) {
        Example example = new Example(Materiel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("mproject", projectId);
        List<Materiel> materielList = materielMapper.selectByExample(example);
        return JsonData.buildSuccess(materielList, "");
    }

    public JsonData delete(Integer id) {
        materielMapper.deleteByPrimaryKey(id);
        return JsonData.buildSuccess("删除成功");
    }

    //添加到已完成
    public JsonData updateStatus(Integer projectId) {
        Materiel materiel = new Materiel();
        materiel.setStatus(1);
        Example example = new Example(Materiel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("mproject", projectId);
        int i = materielMapper.updateByExampleSelective(materiel, example);
        return JsonData.buildSuccess(i, "成功");
    }
}
