package com.example.zheng.controller.dto;

import com.example.zheng.entity.Admin;
import lombok.Data;

/**
 *
 *
 * 创建返回值的【管理员】
 * 不需要返回密码
 * @author shkstart
 * @create 2022-11-11 9:07
 */

@Data
public class LoginDTO {

    private Integer id;
    private String username;
    private String phone;
    private String email;
    private String token;
}
