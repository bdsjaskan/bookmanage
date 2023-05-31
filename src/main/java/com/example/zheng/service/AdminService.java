package com.example.zheng.service;

import com.example.zheng.controller.dto.LoginDTO;
import com.example.zheng.controller.request.BaseRequest;
import com.example.zheng.controller.request.LoginRequest;
import com.example.zheng.controller.request.PasswordRequest;
import com.example.zheng.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author shkstart
 * @create 2022-11-10 16:40
 */
public interface AdminService {


    List<Admin> list();


    PageInfo<Admin> page(BaseRequest baseRequest);

    void save(Admin obj);

    Admin getById(Integer id);

    void update(Admin obj);

    void deleteById(Integer id);

    LoginDTO login(LoginRequest request);

    void changePass(PasswordRequest request);


}
