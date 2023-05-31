package com.example.zheng.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.log.Log;
import com.example.zheng.controller.request.BaseRequest;
import com.example.zheng.entity.Book;


import com.example.zheng.exception.ServiceException;
import com.example.zheng.mapper.BookMapper;
import com.example.zheng.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * @author shkstart
 * @create 2022-11-15 1:17
 */
@Service
public class BookServiceImpl implements BookService {
    
    @Resource
    BookMapper bookMapper;


    @Override
    public List<Book> list() {
        List<Book> categroys = bookMapper.list();
        return categroys;
    }

    @Override
    public PageInfo<Book> page(BaseRequest baseRequest) {


        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());


        // 自关联查询
        List<Book> books = bookMapper.listByCondition(baseRequest);


        return new PageInfo<>(books);

    }

    @Override
    public void save(Book obj) {

        try {
            obj.setCategory(category(obj.getCategories()));
            obj.setUpdatetime(LocalDate.now());
            bookMapper.save(obj);
        } catch (Exception e) {

            throw new ServiceException("数据插入错误",e);
        }

    }

    @Override
    public Book getById(Integer id) {

        return bookMapper.getById(id);
    }

    @Override
    public void update(Book obj) {
        try {
            obj.setUpdatetime(LocalDate.now());
            bookMapper.updateById(obj);
        } catch (Exception e) {
            throw new ServiceException("数据更新错误",e);


        }

    }

    @Override
    public void deleteById(Integer id) {

        bookMapper.deleteById(id);


    }



    private String category(List<String> categories){



        StringBuilder sb = new StringBuilder();

        if (CollUtil.isNotEmpty(categories)){
            categories.forEach(v -> sb.append(v).append(">"));

            return sb.substring(0,sb.lastIndexOf(">"));
        }

        return sb.toString();

    }







}
