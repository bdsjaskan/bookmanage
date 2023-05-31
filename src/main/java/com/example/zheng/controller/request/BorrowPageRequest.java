package com.example.zheng.controller.request;


import lombok.Data;

/**
 * @author shkstart
 * @create 2022-11-16 16:41
 */

@Data
public class BorrowPageRequest extends BaseRequest {
    private String bookName;
    private String bookNo;
    private String userName;
}
