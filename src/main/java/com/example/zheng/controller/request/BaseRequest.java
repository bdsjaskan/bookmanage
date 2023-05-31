package com.example.zheng.controller.request;

import lombok.Data;

/**
@author shkstart
@create 2022-11-08 21:22
*/

@Data
public class BaseRequest {
    private Integer pageNum = 1;
    private Integer pageSize = 10;

}
