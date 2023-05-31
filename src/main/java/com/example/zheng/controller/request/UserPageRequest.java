package com.example.zheng.controller.request;

import lombok.Data;

/**
 * @author shkstart
 * @create 2022-11-08 21:20
 */

@Data
public class UserPageRequest extends BaseRequest{



    private String name;
    private String phone;
}
