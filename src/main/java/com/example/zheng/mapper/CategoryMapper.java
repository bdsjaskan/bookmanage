package com.example.zheng.mapper;

import com.example.zheng.controller.request.BaseRequest;
import com.example.zheng.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author shkstart
 * @create 2022-11-14 19:15
 */
@Mapper
public interface CategoryMapper {

    List<Category> list();


    List<Category> listByCondition(BaseRequest baseRequest);

    void save(Category obj);

    Category getById(Integer id);

    void updateById(Category obj);

    void deleteById(Integer id);
}
