package com.example.materials.service;

import com.example.materials.domain.Detail;
import com.example.materials.domain.Money;
import com.example.materials.mapper.DetailMapper;
import com.example.materials.mapper.MoneyMapper;
import com.example.materials.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class MoneyService {

    @Autowired
    private MoneyMapper moneyMapper;
    @Autowired
    private DetailMapper detailMapper;

    public Money findById(Integer id) {
        //先查出项目对应的金额表
        Example example=new Example(Money.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("project",id);
        Money money= moneyMapper.selectOneByExample(example);
        //再查金额对应的金额详情表
        Example example1=new Example(Detail.class);
        Example.Criteria criteria1=example1.createCriteria();
        criteria1.andEqualTo("money",money.getId());
        money.setDetailList(detailMapper.selectByExample(example1));
        return money;
    }

    //更新
    public JsonData update(Money money) {

        int i=moneyMapper.updateByPrimaryKeySelective(money);
        if(i==1){
            return JsonData.buildSuccess("更新成功");
        }
        return  JsonData.buildError("更新失败");
    }

    //保存
    public JsonData save(Money money) {
        int i=moneyMapper.insertSelective(money);
        if(i==1){
            return JsonData.buildSuccess("保存成功");
        }
        return  JsonData.buildError("保存失败");
    }
}
