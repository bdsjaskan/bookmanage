package com.example.zheng.service.impl;

import com.example.zheng.controller.request.BaseRequest;
import com.example.zheng.entity.Category;
import com.example.zheng.mapper.CategoryMapper;

import com.example.zheng.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * @author shkstart
 * @create 2022-11-14 17:24
 */


@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {


    @Resource
    CategoryMapper categoryMapper;



    @Override
    public List<Category> list() {
        List<Category> categroys = categoryMapper.list();
        return categroys;
    }

    @Override
    public PageInfo<Category> page(BaseRequest baseRequest) {


        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());


        // 自关联查询
        List<Category> categroys = categoryMapper.listByCondition(baseRequest);


        return new PageInfo<>(categroys);

    }

    @Override
    public void save(Category obj) {

        categoryMapper.save(obj);

    }

    @Override
    public Category getById(Integer id) {

        return categoryMapper.getById(id);
    }

    @Override
    public void update(Category obj) {
        obj.setUpdatetime(LocalDate.now());
            categoryMapper.updateById(obj);

    }

    @Override
    public void deleteById(Integer id) {

        categoryMapper.deleteById(id);

    }


}
