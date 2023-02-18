package com.lostandfound.controller;

import com.lostandfound.mapper.DynamicMapper;
import com.lostandfound.pojo.Dynamic;
import com.lostandfound.pojo.Comment;
import com.lostandfound.service.DynamicService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: LostandFound
 * @description:
 * @author: Mr.Like0
 * @create: 2021-11-25 16:12
 **/

@RestController
@Api("加载动态信息的控制器")
public class DynamicController {

    @Autowired
    private DynamicService dynamicService;

    @Autowired
    private DynamicMapper dynamicMapper;

    @GetMapping({"/index","/"})
    @ApiOperation("一进入这个首页，就会加载数据里面所有的动态的信息")
    public List<Dynamic> loadDynamic(){
        return dynamicService.loadAllDynamic();
    }


    @PostMapping("/add")
    @ApiOperation("增加动态的控制器 " +
                  "在发布的时候，评论集合传个空值就行，user不用传，但是用户一定要先登录，其他两个参数可以为空，动态的id和动态发布时间也传空值,若是增加失物招领的信息，售价那些信息都不填，传null")
    public void add_dynamic(@RequestBody Dynamic dynamic, HttpServletRequest request){
        HttpSession session = request.getSession();


        dynamic.getUser().setId((int)session.getAttribute("loginUserId"));

        dynamicService.doAddDynamic(dynamic);

        session.setAttribute("uid",dynamic.getUser().getId());
       session.setAttribute("dtext",dynamic.getDescriptiontext());
       session.setAttribute("dtag",dynamic.getTag());
    }

    @PostMapping("/releaseComment")
    @ApiOperation("发表评论控制器,登陆后后台会自动获取这个用户的id进行对应，所以用户需要先登录才能发表评论")
    public void add_comment(@RequestBody Comment comment,HttpSession sessiom){
        comment.getUser().setId((int)sessiom.getAttribute("loginUserId"));
        dynamicService.doReleaseComments(comment);
    }

    @PostMapping("/updateLikes")
    @ApiOperation("对点赞数进行更新")
    public void updatelikes(int likes , int dynamicId){
        dynamicService.updateLikes(likes,dynamicId);
    }


    @GetMapping({"/tagDynamic"})
    @ApiOperation("根据动态的类型加载数据")
    public List<Dynamic> loadDynamicBytag(String tag){
        return dynamicService.loadtagDynamic(tag);
    }


    @PostMapping("/updateDynamic")
    @ApiOperation("用于添加详细信息")
    public String  update_dynamic(@RequestBody Dynamic dynamic){
            dynamic.setId(dynamicMapper.addNewDId());
            dynamicService.updateDynamic(dynamic);
            return "update success";
    }

    @PostMapping("/updateDynamicmessage")
    @ApiOperation("用于修改动态的相关内容")
    public String  update_dynamicmessage(String descriptiontext,String  originalPrice,Integer  quality,String  sellPrice,Integer  dynamicID){
        if(descriptiontext==null|| originalPrice==null|| quality==null|| sellPrice==null|| dynamicID==null){
            return "parameter can't be null!";
        }
        else {
            Dynamic dynamic = new Dynamic();
            dynamic.setDescriptiontext(descriptiontext);
            dynamic.setOriginalPrice(originalPrice);
            dynamic.setQuality(quality);
            dynamic.setSellPrice(sellPrice);
            dynamic.setId(dynamicID);
            dynamicService.updateDynamic(dynamic);
            return "update success";
        }
    }




    @PostMapping("/deleteDynamic")
    @ApiOperation("用于删除动态")
    public void deleteDynamic(int id){
        dynamicService.doDeleteDynamic(id);
    }


    @PostMapping("/deleteComment")
    @ApiOperation("用于删除评论,只需要传入Commentid,删除成功会返回delete success,不能删除会返回can't delete")
    public String deleteComment(int Commentid,HttpSession session){

        Integer selectCommentId = dynamicService.getCommentID(Commentid);

        if(selectCommentId==null){
            return "There is something wrong with your Commentid";
        }

        else{
            int userID = dynamicService.dogetCommentUserID(Commentid);

            if(userID==(int)session.getAttribute("loginUserId")){
                dynamicService.doDeleteComment(Commentid);
                return "delete success";
            }
            else{
                return "can't delete";
            }

        }
    }



    @PostMapping("/loadDynamicByuserID")
    @ApiOperation("什么也不需要传，前提是你已经登录")
    public List<Dynamic> loadDynamicByuserID(HttpSession session){

        return dynamicService.getMyDynamic((int)session.getAttribute("loginUserId"));
    }


}
