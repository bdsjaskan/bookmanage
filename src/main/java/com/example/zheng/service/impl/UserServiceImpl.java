package com.example.zheng.service.impl;

import com.example.zheng.mapper.UserMapper;
import com.example.zheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author menglq
 * @date 2022 2022/11/8 11:13
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public String get() {
        return userMapper.get();
    }
}
