package com.example.zheng.controller;

import com.example.zheng.common.Result;
import com.example.zheng.controller.dto.LoginDTO;
import com.example.zheng.controller.request.AdminPageRequest;
import com.example.zheng.controller.request.LoginRequest;
import com.example.zheng.controller.request.PasswordRequest;
import com.example.zheng.entity.Admin;
import com.example.zheng.service.AdminService;
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
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;



    @GetMapping("/list")
    public Result list(){

        List<Admin> list = adminService.list();

        return Result.success(list);


    }


    /**
     * 修改管理员密码
     * @param
     * @return
     */
    @PutMapping("/password")
    public Result password( PasswordRequest passwordRequest){
        adminService.changePass(passwordRequest);
        return Result.success();
    }


    @GetMapping("/page")
    public Result page(AdminPageRequest adminPageRequest){
        return Result.success(adminService.page(adminPageRequest));
    }


    /**
     * 添加
     * @param
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody Admin admin){
        adminService.save(admin);
        return Result.success();
    }
    /**
     * 添加
     * @param
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request){
        LoginDTO login = adminService.login(request);
        return Result.success(login);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){

        Admin byId = adminService.getById(id);

        return Result.success(byId);


    }


    /**
     * 修改
     * @param
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin){
        adminService.update(admin);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        adminService.deleteById(id);
        return Result.success();
    }





}
