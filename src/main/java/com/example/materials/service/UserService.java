package com.example.materials.service;

import com.example.materials.domain.User;
import com.example.materials.mapper.UserMapper;
import com.example.materials.utils.JsonData;
import com.example.materials.utils.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
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
    public JsonData  login(User user) {
        Subject subject = SecurityUtils.getSubject();
        subject.isAuthenticated();
        SimpleHash simpleHash = new SimpleHash("MD5", user.getPassword(), user.getUsername(), 12);
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), simpleHash.toString());

        try {
            subject.login(token);
            return JsonData.buildSuccess(token,"登录成功");
        } catch (UnknownAccountException e) {
            return JsonData.buildError("用户名错误");
        } catch (IncorrectCredentialsException e) {
            return JsonData.buildError("密码错误");
        }

    }

    //添加用户
    public JsonData save(User user) {
        if (!checkUsername(user.getUsername())){
            return JsonData.buildError("该用户名已存在，添加失败");
        }
        //密码加密
        SimpleHash simpleHash = new SimpleHash("MD5", user.getPassword(), user.getUsername(), 12);
        user.setPassword(simpleHash.toString());
        int i = userMapper.insertSelective(user);
        if (i != 1){
            return JsonData.buildError("添加失败");
        }
        return JsonData.buildSuccess("添加成功");
    }

    public User findByUsername(String username) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        return userMapper.selectOneByExample(example);
    }

    //校验用户名是否存在
    public boolean checkUsername(String username){
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        User user = userMapper.selectOneByExample(example);
        return user == null;
    }

    public JsonData delete(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return JsonData.buildError("没有此id的账户");
        }
        else if ("admin".equals(user.getUsername()) || id == 1){
            return JsonData.buildError("管理员账户不允许删除");
        }
        userMapper.deleteByPrimaryKey(id);
        return JsonData.buildSuccess("删除用户成功");
    }

    public PageResult<User> findByPage(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        Page<User> userPage = (Page<User>) userMapper.selectAll();
        return new PageResult<>(userPage.getTotal(), userPage.getPages(), userPage.getResult());
    }

    public JsonData update(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",user.getUsername());
        criteria.andNotEqualTo("id", user.getId());
        User u = userMapper.selectOneByExample(example);

        if (u != null){
            return JsonData.buildError("用户名重复");
        }
        if (user.getPassword() != null){
            //密码加密
            SimpleHash simpleHash = new SimpleHash("MD5", user.getPassword(), user.getUsername(), 12);
            user.setPassword(simpleHash.toString());
        }

        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i != 1){
            return JsonData.buildError("更新失败");
        }
        return JsonData.buildSuccess("更新成功");
    }
}
