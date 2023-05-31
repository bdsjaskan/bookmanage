package com.example.zheng.controller.request;

import lombok.Data;

/**
 * @author shkstart
 * @create 2022-11-13 14:20
 */

@Data
public class PasswordRequest {
    private String username;
    private String password;
    private String newPass;
}
