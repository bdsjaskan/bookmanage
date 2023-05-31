package com.example.zheng.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.beans.Transient;
import java.util.Date;

/**
 * @author shkstart
 * @create 2022-11-08 14:40
 */


@Data
public class User {


    private Integer id;
    private String name;
    private String username;
    private Integer age;
    private Integer account;
//    @Transient
    private Integer score;
    private String sex;
    private String phone;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updatetime;
    private boolean status;


}
