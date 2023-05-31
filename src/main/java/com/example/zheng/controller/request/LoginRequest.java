package com.example.zheng.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shkstart
 * @create 2022-11-11 9:03
 */

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class LoginRequest {


    private String username;
    private String password;
}
