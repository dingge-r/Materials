package com.example.materials.service;

import com.example.materials.domain.Contract;
import com.example.materials.mapper.ContractMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ContractService {

    @Autowired
    private ContractMapper contractMapper;

    //查询所有
    public List<Contract> findAll(){
        return contractMapper.selectAll();
    }

    //通过id查询
    public Contract findByid(int id){
        return contractMapper.selectByPrimaryKey(id);
    }

    //更新
    public  int  update(Contract contract){
        return contractMapper.updateByPrimaryKeySelective(contract);
    }

    //删除
    public  int delete(int id){
        return contractMapper.deleteByPrimaryKey(id);
    }

     //保存
    public int save(Contract contract){
        return contractMapper.insertSelective(contract);
    }
}
