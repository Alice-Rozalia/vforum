package org.vforum.model.vo;

import lombok.Data;
import lombok.ToString;
import org.vforum.model.entity.Channel;
import org.vforum.model.entity.Column;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/26 11:32
 */
@Data
@ToString
public class ChannelColumnVo extends Channel {

    private List<Column> children;
}
