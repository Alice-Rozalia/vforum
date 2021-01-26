package org.vforum.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/25 18:24
 */
@Data
@Table(name = "tb_column")
@ToString
@ApiModel(value="Column对象", description="专栏表")
public class Column implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "专栏名称")
    @NotBlank(message = "专栏名称不能为空！")
    private String name;

    @ApiModelProperty(value = "专栏介绍")
    @NotBlank(message = "专栏介绍不能为空！")
    private String introduction;

    @ApiModelProperty(value = "是否启用")
    private Boolean state;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "修改时间")
    private Long updateTime;

    @ApiModelProperty(value = "贴数")
    private Integer subject;

    @ApiModelProperty(value = "所属频道")
    private Integer ChannelId;

    @ApiModelProperty(value = "专栏封面")
    private String image;
}
