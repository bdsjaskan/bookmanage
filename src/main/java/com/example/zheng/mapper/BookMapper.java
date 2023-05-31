package com.example.zheng.mapper;

import com.example.zheng.controller.request.BaseRequest;
import com.example.zheng.entity.Book;
import com.example.zheng.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author shkstart
 * @create 2022-11-15 0:04
 */

@Mapper
public interface BookMapper {

    List<Book> list();


    List<Book> listByCondition(BaseRequest baseRequest);

    void save(Book obj);

    Book getById(Integer id);

    void updateById(Book obj);

    void deleteById(Integer id);


    Book getByNo(String bookNo);

    void updateNumByNo(String bookNo);
}
