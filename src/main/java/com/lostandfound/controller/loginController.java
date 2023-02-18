package com.lostandfound.controller;

import com.lostandfound.pojo.User;
import com.lostandfound.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


/**
 * @program: LostandFound
 * @description:
 * @author: Mr.Like
 * @create: 2021-11-22 20:37
 **/

@RestController
@Api("登录控制器")

public class loginController {


    @Autowired
    private UserService userService;


    @GetMapping("/login")
    @ApiOperation("输入用户的id和密码，查询用户信息，如果数据库没有或者密码不对应则会返回空值")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query",name = "id",value = "用户的id",required = true,dataType = "Integer",dataTypeClass = Integer.class,example = "123"),
                         @ApiImplicitParam(paramType = "query",name = "password", value = "密码",required = true,dataType = "String",dataTypeClass = String.class)
    })
    public User login(@RequestParam("id")int id,String email, int phoneNumber,String name,@RequestParam("password") String password, HttpSession session){

        User loginUser = userService.doLogin(id,password);

        if(loginUser  == null){
            return null;
        }else{
            session.setAttribute("loginUserId",loginUser.getId() );
           // session.
            //将登录的user存入session
            return loginUser ;
        }
    }

    @GetMapping("/logout")
    @ApiOperation("用户点击可以退出登录")
    public void logout(HttpSession session) {
        session.removeAttribute("loginUserId");
    }

}
