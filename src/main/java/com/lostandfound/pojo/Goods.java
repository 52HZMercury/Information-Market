package com.lostandfound.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: LostandFound
 * @description:
 * @author: Mr.Like
 * @create: 2022-03-19 10:53
 **/


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Goods {

    @ApiModelProperty("该商品的id")
    private int id;

    @ApiModelProperty("添加该商品的用户的id")
    private int userId;

    @ApiModelProperty("商品的品牌")
    private  String brand;


    @ApiModelProperty("商品的原价")
    private  String originalPrice;

    @ApiModelProperty("商品售价")
    private  String  sellPrice;

    @ApiModelProperty("是否砍价")
    private boolean ISbargain;

    @ApiModelProperty("商品描述")
    private String description;
}
