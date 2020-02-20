package com.example.materials.service;

import com.example.materials.domain.User;
import com.example.materials.mapper.UserMapper;
import com.example.materials.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;

    //登录
    public JsonData login(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", user.getUsername());
        User user1 = userMapper.selectOneByExample(example);
        if (user1 == null || !user.getPassword().equals(user1.getPassword())){
            return JsonData.buildError("用户名或密码错误");
        }
        return JsonData.buildSuccess("登录成功");

    }

    //添加用户
    public JsonData save(User user) {
        if (!checkUsername(user.getUsername())){
            return JsonData.buildError("该用户名已存在，添加失败");
        }
        int i = userMapper.insertSelective(user);
        if (i != 1){
            return JsonData.buildError("添加失败");
        }
        return JsonData.buildSuccess("添加成功");
    }

    //校验用户名是否存在
    public boolean checkUsername(String username){
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        User user = userMapper.selectOneByExample(example);
        return user == null;
    }
}
