package com.example.materials.service;

import com.example.materials.domain.Contract;
import com.example.materials.mapper.ContractMapper;
import com.example.materials.utils.DateUtils;
import com.example.materials.utils.JsonData;
import com.example.materials.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;


public class ContractService {

    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private ProjectService projectService;

    //查询所有
//    public PageResult<Contract> findByPage(Integer page, Integer rows){
//        PageHelper.startPage(page,rows);
//        Page<Contract> userPage = (Page<Contract>) contractMapper.selectAll();
//        for(Contract contract:userPage){
//            contract.setProjectName(projectService.findNameById(contract.getCproject()));
//        }
//        return new PageResult<>(userPage.getTotal(), userPage.getPages(), userPage.getResult());
//    }

    //通过id查询
    public Contract findByid(int id){
        Example example = new Example(Contract.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cproject",id);
        return contractMapper.selectOneByExample(example);
    }

    //更新
    public  JsonData  update(Contract contract){
        int i = contractMapper.updateByPrimaryKeySelective(contract);
        if (i != 1){
            return JsonData.buildError("更新失败");
        }
        return JsonData.buildSuccess("更新成功");
    }

     //保存
    public JsonData save(Contract contract){
        int i= contractMapper.insertSelective(contract);
        if (i != 1){
            return JsonData.buildError("保存失败");
        }
        return JsonData.buildSuccess("保存成功");
    }
}
