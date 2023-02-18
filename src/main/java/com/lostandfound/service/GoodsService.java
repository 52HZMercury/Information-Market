package com.lostandfound.service;

import com.jd.platform.async.executor.Async;
import com.jd.platform.async.wrapper.WorkerWrapper;
import com.lostandfound.mapper.GoodsMapper;
import com.lostandfound.pojo.Goods;
import com.lostandfound.pojo.User;
import com.lostandfound.service.goodsworkers.goodsWorker1;
import com.lostandfound.service.goodsworkers.goodsWorker2;
import com.lostandfound.service.goodsworkers.goodsWorker3;
import com.lostandfound.service.loginworkers.loginWorker1;
import com.lostandfound.service.loginworkers.loginWorker2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: LostandFound
 * @description:
 * @author: Mr.Like
 * @create: 2022-03-19 10:59
 **/
@Service
public class GoodsService {


    @Autowired
    private GoodsMapper goodsMapper;

    public void add_goods(Goods goods){
        goodsMapper.add_goods(goods);
    }

    public List<Goods> queryAllgoodslistByuserId(int id){
        return goodsMapper.queryAllgoodslistByuserId(id);
    }

    public void deletegoods(int id){
        goodsMapper.deletegoods(id);
    }

    public List<Goods> dogetmysellgoods(int userid){
       return goodsMapper.getmysellgoods(userid);
    }

    public void  dodeletemysellgoods(int dynamicId){
        goodsMapper.deletemysellgoods(dynamicId);
    }
}
