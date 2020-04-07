package com.example.materials.service;

import com.example.materials.domain.Picture;
import com.example.materials.mapper.PictureMapper;
import com.example.materials.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    public JsonData uploadImage(MultipartFile img) {
        try {
            if (img == null) {
                return JsonData.buildError("图片不能为空");
            }
            Map result = new HashMap();
            String  imgName = img.getOriginalFilename();
            String suffixName = imgName.substring(imgName.lastIndexOf("."));

            UUID uuid = UUID.randomUUID();  //随机不重复的字符串
            imgName = uuid + suffixName;

            File dir = new File("C:\\materials\\image");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            img.transferTo(new File(dir, imgName));

            String url = "http://39.107.65.22:8888/materials/image/" + imgName;
            result.put("url", url);
            return JsonData.buildSuccess(result, "上传成功");
        } catch (Exception e) {
            return JsonData.buildError(e, "上传失败，其他错误");
        }
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

