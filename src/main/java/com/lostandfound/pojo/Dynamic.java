package com.lostandfound.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @program: LostandFound
 * @description:
 * @author: Mr.Like
 * @create: 2021-11-25 16:03
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("动态信息的模型")
public class Dynamic {

    @ApiModelProperty("发布动态的用户")
    private User user;

    @ApiModelProperty("每个动态有一个标识")
    private int id;

    @ApiModelProperty("动态发布的时间")
    private Date releasetime;

    @ApiModelProperty("是否隐身")
    private boolean invisible;

    @ApiModelProperty("该条动态的类型，比如失物招领，或者二手交易")
    private String tag;

    @ApiModelProperty("用户的描述文本")
    private String descriptiontext;

    @ApiModelProperty("保存的图片的位置信息")
    private String img;

    @ApiModelProperty("该条动态的点赞数")
    private int likes;

    @ApiModelProperty("对这条动态评论")
    private List<Comment> Comments;

    @ApiModelProperty("买东西或者卖东西")
    private  boolean buyORsale;

    @ApiModelProperty("商品的品牌")
    private  String brand;

    @ApiModelProperty("商品型号")
    private  String xingHao;

    @ApiModelProperty("商品的成色，这里使用int数值1-5表示成色")
    private  int quality;

    @ApiModelProperty("商品的原价")
    private  String originalPrice;

    @ApiModelProperty("商品售价")
    private  String  sellPrice;

    @ApiModelProperty("是否砍价")
    private boolean ISbargain;





}
