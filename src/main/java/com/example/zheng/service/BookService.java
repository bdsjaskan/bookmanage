package com.example.zheng.service;

import com.example.zheng.controller.request.BaseRequest;
import com.example.zheng.entity.Book;
import com.example.zheng.entity.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author shkstart
 * @create 2022-11-15 1:16
 */
public interface BookService {

    List<Book> list();


    PageInfo<Book> page(BaseRequest baseRequest);

    void save(Book obj);

    Book getById(Integer id);

    void update(Book obj);

    void deleteById(Integer id);

}
