package org.vforum.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/26 22:44
 */
@Data
@ToString
@ApiModel(value = "Comment对象", description = "评论表")
public class Comment implements Serializable {

    @Id
    @ApiModelProperty(value = "主键id")
    private String _id;

    @ApiModelProperty(value = "文章id")
    private String articleId;

    @ApiModelProperty(value = "评论内容")
    @NotBlank(message = "评论内容不能为空！")
    private String content;

    @ApiModelProperty(value = "评论用户的id")
    private String userId;

    @ApiModelProperty(value = "评论的父节点")
    private String parentId;

    @ApiModelProperty(value = "评论时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date publishTime;

    @ApiModelProperty(value = "点赞数")
    private Integer thumbup;
}
