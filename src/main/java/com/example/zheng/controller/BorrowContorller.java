package com.example.zheng.controller;

import com.example.zheng.common.Result;
import com.example.zheng.controller.request.BookPageRequest;
import com.example.zheng.controller.request.BorrowPageRequest;
import com.example.zheng.entity.Borrow;
import com.example.zheng.entity.Retur;
import com.example.zheng.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author shkstart
 * @create 2022-11-14 19:26
 */

@CrossOrigin  //解决跨域问题
@RestController
@RequestMapping("/borrow")
public class BorrowContorller {

    @Autowired
    BorrowService borrowService;



    @GetMapping("/list")
    public Result list(){

        List<Borrow> list = borrowService.list();

        return Result.success(list);


    }


    @GetMapping("/page")
    public Result page(BorrowPageRequest borrowPageRequest){
        return Result.success(borrowService.page(borrowPageRequest));
    }


    /**
     * 添加
     * @param
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody Borrow borrow){
        borrowService.save(borrow);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Borrow byId = borrowService.getById(id);

        return Result.success(byId);
    }


    /**
     * 修改
     * @param
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Borrow borrow){
        Date date = new Date();
        borrowService.update(borrow);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        borrowService.deleteById(id);
        return Result.success();
    }




    @GetMapping("/pageRetur")
    public Result pageRetur(BorrowPageRequest borrowPageRequest){
        return Result.success(borrowService.pageRetur(borrowPageRequest));
    }


    /**
     * 添加
     * @param
     * @return
     */
    @PostMapping("/saveRetur")
    public Result saveRetur(@RequestBody Retur obj){
        borrowService.saveRetur(obj);
        return Result.success();
    }



    //  timeRange: week month month2 month3
    @GetMapping("/lineCharts/{timeRange}")
    public Result lineCharts(@PathVariable String timeRange) {
        return Result.success(borrowService.getCountByTimeRange(timeRange));
    }



    @DeleteMapping("/deleteRetur/{id}")
    public Result deleteRetur(@PathVariable Integer id){
        borrowService.deleteReturById(id);
        return Result.success();
    }


}
