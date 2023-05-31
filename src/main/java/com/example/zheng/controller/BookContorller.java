package com.example.zheng.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerResponse;
import com.example.zheng.common.Result;
import com.example.zheng.controller.request.BookPageRequest;
import com.example.zheng.entity.Admin;
import com.example.zheng.entity.Book;
import com.example.zheng.service.BookService;
import com.example.zheng.utils.ToKenUtils;
import com.sun.deploy.net.URLEncoder;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * @author shkstart
 * @create 2022-11-14 19:26
 */
@Slf4j
@CrossOrigin  //解决跨域问题
@RestController
@RequestMapping("/book")
public class BookContorller {

    @Autowired
    BookService bookService;


    //   获取当前的项目的根目录 +  files
    String BASE_FILE_PATH = System.getProperty("user.dir") + "/files/";
    @PostMapping("/file/upload")
    public Result uplaodfile(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();
        long flag = System.currentTimeMillis();
        String filePath = BASE_FILE_PATH + flag + "_" + originalFilename;


        try {
            FileUtil.mkParentDirs(filePath);//创建父级目录
            file.transferTo(FileUtil.file(filePath) );
            Admin currentAdmin = ToKenUtils.getCurrentAdmin();
            String token = ToKenUtils.genToken(currentAdmin.getId().toString(), currentAdmin.getPassword(),15);

            String url = "http://localhost:80/api/book/file/download/" + flag + "?&token=" + token;

            if (originalFilename.endsWith("png")|| originalFilename.endsWith("jpg")||originalFilename.endsWith("pdf")){

                url += "&play=1";
            }


            return Result.success(url);
        } catch (IOException e) {
            log.error("文件上传失败",e);
        }
        return Result.error("文件上传失败");

    }




    @GetMapping("/file/download/{flag}")
    public void downloadFile(@PathVariable String flag , @RequestParam(required = false) String play, HttpServletResponse response) throws IOException {

        OutputStream os;
        List<String> fileNames = FileUtil.listFileNames(BASE_FILE_PATH);
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try {
            if (StrUtil.isNotEmpty(fileName)) {
                String reaLName = fileName.substring(fileName.indexOf("_") + 1);
                if ("1".equals(play)) {
                    response.addHeader("Content-Disposition", "inline; filename=" + URLEncoder.encode(reaLName, "UTF-8"));
                } else {
                    response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(reaLName, "UTF-8"));
                }

                byte[] bytes = FileUtil.readBytes(BASE_FILE_PATH + fileName);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }


        }catch (Exception e){

            log.error("文件下载失败",e);



        }
    }


    @GetMapping("/list")
    public Result list(){

        List<Book> list = bookService.list();

        return Result.success(list);


    }










    @GetMapping("/page")
    public Result page(BookPageRequest bookPageRequest){
        return Result.success(bookService.page(bookPageRequest));
    }


    /**
     * 添加
     * @param
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody Book book){
        bookService.save(book);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){

        Book byId = bookService.getById(id);

        return Result.success(byId);


    }


    /**
     * 修改
     * @param
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Book book){
        Date date = new Date();
        bookService.update(book);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        bookService.deleteById(id);
        return Result.success();
    }



}
