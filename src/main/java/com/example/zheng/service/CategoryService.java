package com.example.zheng.service;

import com.example.zheng.controller.request.BaseRequest;
import com.example.zheng.entity.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author shkstart
 * @create 2022-11-14 17:23
 */
public interface CategoryService {



    List<Category> list();


    PageInfo<Category> page(BaseRequest baseRequest);

    void save(Category obj);

    Category getById(Integer id);

    void update(Category obj);

    void deleteById(Integer id);


}
