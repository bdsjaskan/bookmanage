package com.example.zheng.mapper;

import com.example.zheng.controller.request.BaseRequest;
import com.example.zheng.entity.Borrow;
import com.example.zheng.entity.Retur;
import com.example.zheng.mapper.po.BorrowReturCountPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author shkstart
 * @create 2022-11-15 0:04
 */

@Mapper
public interface BorrowMapper {

    List<Borrow> list();


    List<Borrow> listByCondition(BaseRequest baseRequest);

    List<Retur> listReturByCondition(BaseRequest baseRequest);

    void save(Borrow obj);
    void saveRetur(Retur obj);

    Borrow getById(Integer id);

    void updateById(Borrow obj);

    void deleteById(Integer id);


    void deleteReturById(Integer id);

    void updateStatus(String status,Integer id);

    List<BorrowReturCountPO> getCountByTimeRange(@Param("timeRange") String timeRange, @Param("type") int type);
}
