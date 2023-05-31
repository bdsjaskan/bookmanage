package com.example.zheng.controller.request;

import lombok.Data;

/**
 * @author shkstart
 * @create 2022-11-08 21:20
 */

@Data
public class AdminPageRequest extends BaseRequest{



    private String username;
    private String phone;
    private String email;
}
