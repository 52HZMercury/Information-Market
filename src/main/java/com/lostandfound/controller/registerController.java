package com.lostandfound.controller;

import com.lostandfound.pojo.User;
import com.lostandfound.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: LostandFound
 * @description:
 * @author: Mr.Like
 * @create: 2022-03-17 14:51
 **/

@RestController
@Api("注册控制器")
public class registerController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation("注册接口,这里返回的id就是用户的id")
    public int register(@RequestParam("username") String username, @RequestParam("password") String password){
        User reuser = new User();
        reuser.setName(username);
        reuser.setPassword(password);

        int regId = userService.doreg(reuser);

        return regId;
        }
}
