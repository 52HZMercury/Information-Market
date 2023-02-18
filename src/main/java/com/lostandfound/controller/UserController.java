package com.lostandfound.controller;


import com.lostandfound.pojo.Dynamic;
import com.lostandfound.pojo.User;
import com.lostandfound.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @program: LostandFound
 * @description: 用户查询自己信息的controller
 * @author: Mr.Like
 * @create: 2022-03-16 19:36
 **/

@RestController
@Api("用户在个人界面查看自己的相关信息的接口")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping({"/userindex"})
    @ApiOperation("首先要登录，然后才会有对应的登录信息，才可以进行这一步的操作")
    public User loadusermessage(HttpSession session){
          return userService.loadUsermessage((int)session.getAttribute("loginUserId"));
    }

    @PostMapping("/userAlter")
    @ApiOperation("用户修改自己的个人信息,这里也需要登录后才能修改，这里只提供name  qqnum telnum introduction的修改，其他数值传空")
    public void alterUserMessage(@RequestBody User upuser,HttpSession session){
        upuser.setId((int)session.getAttribute("loginUserId"));
        userService.doAlter(upuser);
    }

    @PostMapping("/userPasswordAlter")
    @ApiOperation("用户修改自己的个人信息,这里也需要登录后才能修改，这里只提供password的修改，其他数值传空")
    public void alterUserpassword(@RequestBody User upuser,HttpSession session){
        upuser.setId((int)session.getAttribute("loginUserId"));
        userService.doAlterpassword(upuser);
    }

}
