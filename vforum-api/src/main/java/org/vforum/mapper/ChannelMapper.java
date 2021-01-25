package org.vforum.mapper;

import org.vforum.model.entity.Channel;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/25 23:37
 */
public interface ChannelMapper extends Mapper<Channel> {

    // 查询未被禁用的频道
    List<Channel> findUsableChannel();
}
