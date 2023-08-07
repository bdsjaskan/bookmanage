package com.example.zheng.service;

import com.example.zheng.controller.request.BaseRequest;
import com.example.zheng.controller.request.UserPageRequest;
import com.example.zheng.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Description:
 *
 * @author menglq
 * @date 2022 2022/11/8 11:12
 */
public interface UserService {
    String get();

    List<User> list();


    PageInfo<User> page(BaseRequest baseRequest);

    void save(User user);

    User getById(Integer id);

    void update(User user);

    void deleteById(Integer id);

    void handleAccount(User user);
}
