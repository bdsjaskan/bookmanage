package com.example.zheng.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author shkstart
 * @create 2022-11-14 23:43
 */
@Data
public class Book extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 出版日期
     */
    private String publishDate;
    /**
     * 作者
     */
    private String author;

    /**
     * 出版社
     */
    private String publisher;

    /**
     * 分类
     */
    private String category;

    /**
     * 标准吗
     */
    private String bookNo;

    /**
     * 封面
     */
    private String cover;

    private List<String> categories;



    private Integer score;


    private Integer nums;

}