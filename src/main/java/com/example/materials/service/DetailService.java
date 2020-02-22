package com.example.materials.service;

import com.example.materials.domain.Detail;
import com.example.materials.mapper.DetailMapper;
import com.example.materials.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DetailService {
    @Autowired
    public DetailMapper detailMapper;

    //保存
    public JsonData save(Detail detail){
        int i=detailMapper.insertSelective(detail);
        if (i != 1){
            return JsonData.buildError("保存失败");
        }
        return JsonData.buildSuccess("保存成功");
    }

    //更新
    public  JsonData update(Detail detail){
        int i=detailMapper.updateByPrimaryKeySelective(detail);
        if (i != 1){
            return JsonData.buildError("更新失败");
        }
        return JsonData.buildSuccess("更新成功");
    }

}
