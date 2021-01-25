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
 * @Date: 2021/1/25 23:30
 */
@Data
@Table(name = "tb_channel")
@ToString
@ApiModel(value="Channel对象", description="频道表")
public class Channel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "频道名称")
    @NotBlank(message = "频道名称不能为空！")
    private String name;

    @ApiModelProperty(value = "是否启用")
    private Boolean state;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "修改时间")
    private Long updateTime;
}
