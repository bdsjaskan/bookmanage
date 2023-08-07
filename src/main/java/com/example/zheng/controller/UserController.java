package com.example.zheng.controller;

import com.example.zheng.common.Result;
import com.example.zheng.controller.request.UserPageRequest;
import com.example.zheng.entity.User;
import com.example.zheng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 *
 * @author menglq
 * @date 2022 2022/11/8 11:14
 */

@CrossOrigin  //解决跨域问题
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public String get(){
        return userService.get();
    }

    @GetMapping("/list")
    public Result list(){

        List<User> list = userService.list();

        return Result.success(list);


    }

    @GetMapping("/page")
    public Result page(UserPageRequest userPageRequest){
        return Result.success(userService.page(userPageRequest));
    }


    /**
     * 添加
     * @param user
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        userService.save(user);
        return Result.success();
    }
    @PostMapping("/account")
    public Result account(@RequestBody User user){
        userService.handleAccount(user);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){

        User user = userService.getById(id);

        return Result.success(user);


    }


    /**
     * 修改
     * @param user
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        userService.deleteById(id);
        return Result.success();
    }





}
