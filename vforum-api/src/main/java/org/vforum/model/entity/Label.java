package org.vforum.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/25 18:51
 */
@Data
@Table(name = "tb_label")
@ToString
@ApiModel(value="Label对象", description="标签表")
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "标签名称")
    private String labelName;

    @ApiModelProperty(value = "状态")
    private Boolean state;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "修改时间")
    private Long updateTime;
}
