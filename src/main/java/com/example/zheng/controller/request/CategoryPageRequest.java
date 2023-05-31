package com.example.zheng.controller.request;

import lombok.Data;

/**
 * @author shkstart
 * @create 2022-11-14 20:01
 */

@Data
public class CategoryPageRequest extends BaseRequest{

    private String name;
    private String remark;

}
