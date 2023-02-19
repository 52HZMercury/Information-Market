package com.lostandfound.service;

import com.lostandfound.mapper.GoodsMapper;
import com.lostandfound.pojo.Goods;
import com.lostandfound.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: LostandFound
 * @description:
 * @author: Mr.Like
 * @create: 2022-03-19 10:59
 **/
@Service
@SuppressWarnings("all")
public class GoodsService {


    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private RedisUtil redisUtil;



    public void add_goods(Goods goods){
        goodsMapper.add_goods(goods);
    }

    public List<Goods> queryAllgoodslistByuserId(int id){
        redisUtil.multi();
        //先从缓存中读取数据
        if(redisUtil.get(id+"") != null){
            return (List<Goods>)redisUtil.get(id+"");
        }else{
            List<Goods> goods = goodsMapper.queryAllgoodslistByuserId(id);
            redisUtil.setGoods(id+"",(ArrayList<Goods>) goods);
            redisUtil.multiexec();
            return goods;
        }
    }

    public void deletegoods(int id){
        redisUtil.multi();
        goodsMapper.deletegoods(id);
        redisUtil.delete(id+"");
        redisUtil.multiexec();
    }

    public List<Goods> dogetmysellgoods(int userid){
        redisUtil.multi();
        //先从缓存中读取数据
        if(redisUtil.get(userid+"") != null){
            return (List<Goods>)redisUtil.get(userid+"");
        }else{
            List<Goods> goods = goodsMapper.getmysellgoods(userid);
            redisUtil.setGoods(userid+"",(ArrayList<Goods>) goods);
            redisUtil.multiexec();
            return goods;
        }
    }

    public void  dodeletemysellgoods(int dynamicId){
        redisUtil.multi();
        goodsMapper.deletemysellgoods(dynamicId);
        redisUtil.delete(dynamicId+"");
        redisUtil.multiexec();
    }
}
