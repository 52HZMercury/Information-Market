package com.lostandfound.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: LostandFound
 * @description:
 * @author: Mr.Like
 * @create: 2021-11-29 17:23
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @ApiModelProperty("该评论的编号")
    private  int CommentId;

    @ApiModelProperty("该评论对应的动态编号")
    private  int dynamicId;
    @ApiModelProperty("发布评论的用户")
    private User user;
    @ApiModelProperty("用户的评论")
    private String commentText;
}
