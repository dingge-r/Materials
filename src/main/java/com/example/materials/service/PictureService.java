package com.example.materials.service;

import com.example.materials.domain.Picture;
import com.example.materials.mapper.PictureMapper;
import com.example.materials.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    public List<Picture> findById(Integer id) {
        Example example = new Example(Picture.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("project", id);
        return pictureMapper.selectByExample(example);
    }

    public JsonData update(Picture picture) {
        int i = pictureMapper.updateByPrimaryKeySelective(picture);
        if (i != 1) {
            return JsonData.buildError("更新失败");
        }
        return JsonData.buildSuccess("更新成功");
    }

    public JsonData save(Picture picture) {
        int i = pictureMapper.insertSelective(picture);
        if (i != 1) {
            return JsonData.buildError("保存失败");
        }
        return JsonData.buildSuccess("保存成功");
    }

    public JsonData delete(Integer id) {
        pictureMapper.deleteByPrimaryKey(id);
        return JsonData.buildSuccess("删除成功");
    }
}

