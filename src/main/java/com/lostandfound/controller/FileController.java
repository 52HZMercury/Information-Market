package com.lostandfound.controller;



import com.lostandfound.mapper.DynamicMapper;
import com.lostandfound.mapper.UserMapper;
import com.lostandfound.service.DynamicService;
import com.lostandfound.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * @program: LostandFound
 * @description:
 * @author: Mr.Like
 * @create: 2021-12-10 21:16
 **/
@RestController
public class FileController {


    @Autowired
    private DynamicService dynamicService;

    @Autowired
    private DynamicMapper dynamicMapper;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/fileload")
    @ApiOperation(value = "上传图片接口",notes = "上传图片接口,注意！！！一定要先发送了动态 (就是先调用了/add接口,然后再调这个)的其他信息以后，最后才上传图片")
    public void fileUpload(@RequestPart("file") MultipartFile file, HttpServletRequest request ) {

        String fileName = file.getOriginalFilename();//获取文件的原始的名字
        String suffixName = fileName.substring(fileName.lastIndexOf("."));//文件后缀
        String filePath = "/www/wwwroot/LostandFound/backgroundSystem/lostandfoundimg/dynamic/";
        // D:/文档/新鲜出炉的程序/SpringBoot/LostandFound/src/main/resources/LostandFoundimg/dynamic
        // linux /www/wwwroot/LostandFound/backgroundSystem/lostandfoundimg/dynamic/
        fileName = UUID.randomUUID() + suffixName;//通过uuid生成唯一标识符
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            dynamicService.addImg("img/dynamic/" +fileName, dynamicMapper.addNewDId());
        }

    }



    @PostMapping(value = "/headImgload")
    @ApiOperation(value = "上传头像图片接口",notes = "前提也是你已经登录")
    public void headImgload(@RequestPart("file") MultipartFile file, HttpServletRequest request ) {

        String fileName = file.getOriginalFilename();//获取文件的原始的名字
        String suffixName = fileName.substring(fileName.lastIndexOf("."));//文件后缀
        String filePath = "/www/wwwroot/LostandFound/backgroundSystem/lostandfoundimg/head/";
        // D:/文档/新鲜出炉的程序/SpringBoot/LostandFound/src/main/resources/LostandFoundimg/dynamic
        // linux /www/wwwroot/LostandFound/backgroundSystem/lostandfoundimg/dynamic/
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            HttpSession session = request.getSession();
            userService.addheadImgPath("img/head/" +fileName, (int)session.getAttribute("loginUserId"));
        }

    }

}
