package com.lostandfound.controller;

import com.lostandfound.pojo.Goods;
import com.lostandfound.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: LostandFound
 * @description:
 * @author: Mr.Like
 * @create: 2022-03-19 10:57
 **/


@RestController
@Api("商品管理器")

public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/addgoodsTocar")
    @ApiOperation("添加商品到购物车 userid后台获取")
    public void addgoodsTocar(@RequestBody Goods goods,HttpSession session){
        goods.setUserId((int)session.getAttribute("loginUserId"));
        goodsService.add_goods(goods);
    }

    @PostMapping("/getgoods")
    @ApiOperation("获取用户购物车里面的全部信息，这里不需要填写其他信息，只需要登录后请求就行，userid后台获取")
    public List<Goods> getuserAllGoods(HttpSession session) {
        return goodsService.queryAllgoodslistByuserId((int)session.getAttribute("loginUserId"));
    }

    @PostMapping("/deletegoods")
    @ApiOperation("删除用户购物车里的商品,这个是商品id")
    public void deletegoodsbyid(int id){
        goodsService.deletegoods(id);
    }

    @PostMapping("/mygoods")
    @ApiOperation("我的闲置,不需要其他什么参数，登录即可")
    public List<Goods> getmysellgoods(HttpSession session){
        return  goodsService.dogetmysellgoods((int)session.getAttribute("loginUserId"));
    }

    @PostMapping("/deletemygoods")
    @ApiOperation("删除我的闲置，即删除发布的二手交易的动态，这里的dynamicId就是闲置物品的Id")
    public void deletemysellgoods(int dynamicId){
        goodsService.dodeletemysellgoods(dynamicId);
    }


}
