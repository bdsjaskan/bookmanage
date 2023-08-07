package com.example.zheng.mapper;

import com.example.zheng.controller.request.UserPageRequest;
import com.example.zheng.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description:
 *
 * @author menglq
 * @date 2022 2022/11/8 11:12
 */

//@Mapper
@Mapper
public interface UserMapper {
    String get();


//    @Select("select * from user")
    List<User> listUsers();

    List<User> listByCondition(UserPageRequest userPageRequest);

    void save(User user);

    User getById(Integer id);


    void updateById(User user);

    void deleteById(Integer id);

    User getByNo(String userNo);

}
