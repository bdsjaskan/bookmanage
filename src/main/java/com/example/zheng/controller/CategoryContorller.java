package com.example.zheng.controller;

import cn.hutool.core.collection.CollUtil;
import com.example.zheng.common.Result;
import com.example.zheng.controller.request.CategoryPageRequest;
import com.example.zheng.entity.Category;
import com.example.zheng.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shkstart
 * @create 2022-11-14 19:26
 */

@CrossOrigin  //解决跨域问题
@RestController
@RequestMapping("/category")
public class CategoryContorller {

    @Autowired
    CategoryService categoryService;



    @GetMapping("/list")
    public Result list(){

        List<Category> list = categoryService.list();

        return Result.success(list);


    }
    @GetMapping("/tree") //关于级联选择器的方法
    public Result tree(){

        List<Category> list = categoryService.list();

        //对list操作即可
        //List<Category> treeList = list.stream().filter(v -> v.getPid() == null).collect(Collectors.toList());


        return Result.success(createTree(null,list));


    }

    // 完全递归的方法来实现递归树

    private List<Category> createTree(Integer pid, List<Category> categories) {
        List<Category> treeList = new ArrayList<>();

        for (Category category :categories) {
            if (pid == null){
                if (category.getPid() == null){//那这就是第一级节点
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(), categories));
                }
            }else {
                if (pid == category .getPid()) {
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(),categories));
                }
            }
            if (CollUtil.isEmpty(category.getChildren())) {

                category.setChildren(null);

            }
        }

        return treeList;
    }



    @GetMapping("/page")
    public Result page(CategoryPageRequest categoryPageRequest){
        return Result.success(categoryService.page(categoryPageRequest));
    }


    /**
     * 添加
     * @param
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody Category categroy){
        categoryService.save(categroy);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){

        Category byId = categoryService.getById(id);

        return Result.success(byId);


    }


    /**
     * 修改
     * @param
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Category categroy){
        categoryService.update(categroy);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        categoryService.deleteById(id);
        return Result.success();
    }


}
