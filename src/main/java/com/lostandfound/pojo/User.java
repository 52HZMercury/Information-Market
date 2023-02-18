package com.lostandfound.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: LostandFound
 * @description:
 * @author: Mr.Like
 * @create: 2021-11-23 10:24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    public User(String name) {
        this.name = name;
    }
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("用户id")
    private int id;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("用户头像")
    private String headImg;
    @ApiModelProperty("用户动态总数")
    private int dynamicSum;
    @ApiModelProperty("点赞总数")
    private int likesSum;
    @ApiModelProperty("qq号")
    private String qqnum;
    @ApiModelProperty("电话号码")
    private String telnum;
    @ApiModelProperty("自我简介")
    private String introduction;


}
