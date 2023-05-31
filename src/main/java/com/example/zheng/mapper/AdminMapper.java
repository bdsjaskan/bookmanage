package com.example.zheng.mapper;

import com.example.zheng.controller.request.*;
import com.example.zheng.entity.Admin;
import com.example.zheng.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 *
 * @author menglq
 * @date 2022 2022/11/8 11:12
 */

//@Mapper
@Mapper
public interface AdminMapper {

    List<Admin> listAdmin();


    List<Admin> listByCondition(BaseRequest baseRequest);

    void save(Admin obj);

    Admin getById(Integer id);

    void updateById(Admin admin);

    void deleteById(Integer id);

    Admin getByUsernameAndPassword(@Param("username") String username,@Param("password") String password);


    int updatePassord(PasswordRequest Request);

    Admin getByUsername(String username);
}
