package com.example.zheng.service;

import com.example.zheng.controller.request.BaseRequest;
import com.example.zheng.entity.Borrow;
import com.example.zheng.entity.Retur;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2022-11-15 1:16
 */
public interface BorrowService {

    List<Borrow> list();


    PageInfo<Borrow> page(BaseRequest baseRequest);

    PageInfo<Retur> pageRetur(BaseRequest baseRequest);

    void save(Borrow obj);

    void saveRetur(Retur obj);

    Borrow getById(Integer id);

    void update(Borrow obj);

    void deleteById(Integer id);

    void deleteReturById(Integer id);

    Map<String,Object> getCountByTimeRange(String timeRange);


}
