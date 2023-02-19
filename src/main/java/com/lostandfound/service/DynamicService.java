package com.lostandfound.service;

import com.lostandfound.mapper.DynamicMapper;
import com.lostandfound.pojo.Dynamic;
import com.lostandfound.pojo.Comment;
import com.lostandfound.pojo.Goods;
import com.lostandfound.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: LostandFound
 * @description:
 * @author: Mr.Like
 * @create: 2021-11-25 16:21
 **/

@Service
public class DynamicService {

    @Autowired
    private DynamicMapper dynamicMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 返回所有的动态信息
     * @return
     */
    public List<Dynamic> loadAllDynamic(){
        //先从缓存中读取数据
        if(redisUtil.rangeDynamic("dynamic",0,-1) != null){
            return redisUtil.rangeDynamic("dynamic",0,-1);
        }else{
            List<Dynamic> dynamic = dynamicMapper.queryDynamicList();
            redisUtil.leftPushAllDynamic("dynamic",dynamic);
            return dynamic;
        }
    }

    /**
     *发表动态
     */
    public void  doAddDynamic(Dynamic dymaic){
       dynamicMapper.addDynamic(dymaic);
    }

    /**
     * 发表评论
     * @param comment
     */
    public void doReleaseComments(Comment comment){
        dynamicMapper.releaseComments(comment);
    }

    public void updateLikes(int likes,int id){
        dynamicMapper.upDateLikes(likes,id);
    }

    public void addImg(String classpath,int dynamicID){
        dynamicMapper.addDynamicImgclasspath(classpath,dynamicID);
    }

    public List<Dynamic> loadtagDynamic(String tag){
        //先从缓存中读取数据
        if(redisUtil.rangeDynamic(tag,0,-1) != null){
            return redisUtil.rangeDynamic(tag,0,-1);
        }else{
            List<Dynamic> dynamic = dynamicMapper.queryDynamicList();
            redisUtil.leftPushAllDynamic(tag,dynamic);
            return dynamic;
        }
    }


    public void updateDynamic(Dynamic dynamic){
         dynamicMapper.updatedynamic(dynamic);
         redisUtil.delete(dynamic.getId()+"");
    }

    public void doDeleteDynamic(int id){
        dynamicMapper.deleteDynamic(id);
        redisUtil.delete(id+"");
    }

    public void doDeleteComment(int id){
        dynamicMapper.deleteComment(id);
    }

    public int dogetCommentUserID(int Commentid){
        //先从缓存中读取数据
        if(redisUtil.get(Commentid+"") != null){
            return (int) redisUtil.get(Commentid+"");
        }else{
            redisUtil.set(Commentid+"",dynamicMapper.getCommentUserID(Commentid)+"");
            return dynamicMapper.getCommentUserID(Commentid);
        }
    }

    public Integer getCommentID(int CommentID){
        return dynamicMapper.getCommentID(CommentID);
    }

    public List<Dynamic> getMyDynamic(int userID){
        //先从缓存中读取数据
        if(redisUtil.rangeDynamic(userID+"",0,-1) != null){
            return redisUtil.rangeDynamic(userID+"",0,-1);
        }else{
            List<Dynamic> dynamic = dynamicMapper.queryDynamicListByUserid(userID);
            redisUtil.leftPushAllDynamic(userID+"",dynamic);
            return dynamic;
        }
    }

}
