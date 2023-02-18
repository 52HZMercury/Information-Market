package com.lostandfound.mapper;


import com.lostandfound.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {

    //添加物品到购物车
    void add_goods(Goods goods);

    //加载该用户的所有购物车信息
    List<Goods> queryAllgoodslistByuserId(int userId);

    //删除购物车里面的商品
    void deletegoods(int id);

    List<Goods> getmysellgoods(int userid);

    void deletemysellgoods(int dynamicId);
}
