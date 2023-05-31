package com.example.zheng.service.impl;


import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.zheng.controller.dto.LoginDTO;
import com.example.zheng.controller.request.BaseRequest;
import com.example.zheng.controller.request.LoginRequest;
import com.example.zheng.controller.request.PasswordRequest;
import com.example.zheng.entity.Admin;
import com.example.zheng.exception.ServiceException;
import com.example.zheng.mapper.AdminMapper;
import com.example.zheng.service.AdminService;
import com.example.zheng.utils.ToKenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author menglq
 * @date 2022 2022/11/8 11:13
 */
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;


    private static final String DEFAULT_PASS = "123";
    private static final String PASS_SALT = "fengge";




    @Override
    public List<Admin> list() {
        List<Admin> admins = adminMapper.listAdmin();


        return admins;
    }

    @Override
    public PageInfo<Admin> page(BaseRequest baseRequest) {


        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());


        List<Admin> admins = adminMapper.listByCondition(baseRequest);


        return new PageInfo<Admin>(admins);

    }

    @Override
    public void save(Admin obj) {

        Date date = new Date();

        obj.setCreatetime(date);

        //当作卡号来处理
//        obj.setUsername(DateUtil.format(date,"yyyyMMdd") + Math.abs(IdUtil.fastSimpleUUID().hashCode()));
//
//

        //设置一个默认密码
        if (StrUtil.isBlank(obj.getPassword())){
            obj.setPassword(DEFAULT_PASS);
        }


        //密码加密（MD5）加盐(diaoyong le 调用了自己创建方法进行Md5加密加盐)
        obj.setPassword(securePass(obj.getPassword()));

            adminMapper.save(obj);



    }

    @Override
    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public void update(Admin obj) {


        obj.setUpdatetime(new Date());


        adminMapper.updateById(obj);





    }

    @Override
    public void deleteById(Integer id) {

        adminMapper.deleteById(id);



    }



    @Override
    public LoginDTO login(LoginRequest request) {


        request.setPassword(securePass(request.getPassword()));


        Admin admin = adminMapper.getByUsernameAndPassword(request.getUsername(),request.getPassword());

        if (admin == null){
            throw new ServiceException("用户名或密码错误");
        }
        if (!admin.isStatus()){
            throw new ServiceException("当前用户处于禁用状态，请联系管理员");
        }

        LoginDTO loginDTO = new LoginDTO();

        BeanUtils.copyProperties(admin,loginDTO);

        //生成token
        String token = ToKenUtils.genToken(String.valueOf(admin.getId()), admin.getPassword());
        loginDTO.setToken(token);

        return loginDTO;
    }


    /**
     * 修改管理员密码
     * @param request
     */
    @Override
    public void changePass(PasswordRequest request) {

        //注意：要对新的密码进行加密

        request.setNewPass(securePass(request.getNewPass()));

        String username = request.getUsername();
        request.setUsername(username);


        int count = adminMapper.updatePassord(request);


        if (count <= 0){

            throw new ServiceException("修改密码失败");

        }
    }


    /**
     * MD5
     * 加密加盐
     * @param password
     * @return
     */
    private String securePass(String password){
        return SecureUtil.md5(password + PASS_SALT);
    }
}
