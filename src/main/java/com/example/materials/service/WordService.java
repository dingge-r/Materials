package com.example.materials.service;

import com.example.materials.domain.Word;
import com.example.materials.mapper.WordMapper;
import com.example.materials.utils.JsonData;
import org.jodconverter.DocumentConverter;
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
public class WordService {
    @Autowired
    private WordMapper wordMapper;

    @Autowired
    private DocumentConverter converter;

    //上传文件
    public JsonData uploadWord(MultipartFile file) {
        try {
            if (file == null) {
                return JsonData.buildError("文件不能为空");
            }
            Map result = new HashMap();
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            UUID uuid = UUID.randomUUID();
            fileName = uuid + suffixName;

            File dir = new File("C:\\materials\\word");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file.transferTo(new File(dir, fileName));

            String url = "http://39.107.65.22:8888/materials/word/" + fileName;
            result.put("url", url);
            return JsonData.buildSuccess(result, "上传成功");
        } catch (Exception e) {
            return JsonData.buildError(e, "上传失败，其他错误");
        }
    }

    public JsonData save(Word word) {
        int i = wordMapper.insertSelective(word);
        if (i != 1) {
            return JsonData.buildError("添加失败");
        }
        return JsonData.buildSuccess("成功");
    }

    public JsonData update(Word word) {
        int i = wordMapper.updateByPrimaryKeySelective(word);
        if (i != 1) {
            return JsonData.buildError("添加失败");
        }
        return JsonData.buildSuccess("成功");
    }

    public JsonData delete(Integer id) {
        wordMapper.deleteByPrimaryKey(id);
        return JsonData.buildSuccess("删除成功");
    }

    public JsonData toPdfFile(String fileUrl) {
        try {
            fileUrl = fileUrl.substring(fileUrl.indexOf("/materials"));
            File file = new File(fileUrl);
            String fileName = file.getName();//daa656e2-a382-43a7-a257-9ce0be810ffb.doc
            String suffixName = fileName.substring(fileName.lastIndexOf(".")); //.doc
            if (".pdf".equals(suffixName)){
                return JsonData.buildSuccess(fileUrl, "成功");
            }
            String name = fileName.substring(0, fileName.lastIndexOf(".")); //daa656e2-a382-43a7-a257-9ce0be810ffb
            File dir = new File("C:\\materials\\pdf");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //文件转化
            converter.convert(file).to(new File("C:\\materials\\pdf\\"+ name +".pdf")).execute();
            String pdfUrl = "http://39.107.65.22:8888/materials/pdf/"+ name +".pdf";
            return JsonData.buildSuccess(pdfUrl, "成功");
        }catch (Exception e){
            return JsonData.buildError(e,"转换失败");
        }
    }
}
