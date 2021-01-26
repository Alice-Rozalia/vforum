package org.vforum.service;

import org.vforum.common.entity.Result;
import org.vforum.model.entity.Channel;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/25 23:33
 */
public interface ChannelService {

    /**
     * 获取所有的频道
     * @return
     */
    Result findAllChannelList();

    /**
     * 添加频道
     * @param channel
     * @return
     */
    Result addChannel(Channel channel);

    /**
     * 修改频道
     * @param channel
     * @return
     */
    Result updateChannel(Channel channel);

    /**
     * 查找未被禁用的频道
     * @return
     */
    Result findUsableChannelAndColumnByTree();

    /**
     * 删除频道
     * @param channelId
     * @return
     */
    Result deleteChannel(Integer channelId);
}
