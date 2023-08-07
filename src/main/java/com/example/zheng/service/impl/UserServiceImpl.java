package com.example.zheng.service.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.example.zheng.controller.request.BaseRequest;
import com.example.zheng.controller.request.UserPageRequest;
import com.example.zheng.entity.User;
import com.example.zheng.mapper.UserMapper;
import com.example.zheng.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author menglq
 * @date 2022 2022/11/8 11:13
 */
@Service
public class UserServiceImpl implements UserService {


    /**
     *
     */
    @Autowired
    private UserMapper userMapper;

    @Override
    public String get() {
        return userMapper.get();
    }

    @Override
    public List<User> list() {

        List<User> users = userMapper.listUsers();

        return users;
    }

    @Override
    public PageInfo<User> page(BaseRequest baseRequest) {

        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());


        List<User> users = userMapper.listByCondition((UserPageRequest) baseRequest);


        return new PageInfo<User>(users);
    }

    @Override
    public void save(User user) {
        
        Date date = new Date();




        //当作卡号来处理
        user.setUsername(DateUtil.format(date,"yyyyMMdd") + Math.abs(IdUtil.fastSimpleUUID().hashCode()));


        user.setCreatetime(date);

        userMapper.save(user);


    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public User getById (Integer id) {
        return userMapper.getById(id);
    }

    /**
     * 根据id修改数据
     * @param user
     */
    @Override
    public void update(User user) {


        user.setUpdatetime(new Date());


        userMapper.updateById(user);

    }

    @Override
    public void deleteById(Integer id) {

        userMapper.deleteById(id);

    }

    @Override
    public void handleAccount(User user) {

        Integer score = user.getScore();

        if (score == null){
            return;
        }
        Integer id = user.getId();
        User dbUser = userMapper.getById(id);
        dbUser.setAccount(dbUser.getAccount() + score);

        userMapper.updateById(dbUser);

    }
}
