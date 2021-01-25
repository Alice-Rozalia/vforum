package org.vforum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.vforum.common.entity.Result;
import org.vforum.common.entity.ResultCode;
import org.vforum.mapper.ChannelMapper;
import org.vforum.model.entity.Channel;
import org.vforum.service.ChannelService;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/25 23:34
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取所有的频道
     *
     * @return
     */
    @Override
    public Result findAllChannelList() {
        List<Channel> channels = channelMapper.selectAll();
        return Result.ok().data("channels", channels);
    }

    /**
     * 添加频道
     *
     * @param channel
     * @return
     */
    @Override
    public Result addChannel(Channel channel) {
        channel.setId(null);
        channel.setCreateTime(System.currentTimeMillis());
        if (channelMapper.insertSelective(channel) == 1) {
            // 添加成功之后删除redis缓存中的 channels 集合
            redisTemplate.delete("usableChannels");
            return Result.ok(ResultCode.ADD_SUCCESS);
        }
        return Result.error(ResultCode.ADD_ERROR);
    }

    /**
     * 修改频道
     *
     * @param channel
     * @return
     */
    @Override
    public Result updateChannel(Channel channel) {
        channel.setUpdateTime(System.currentTimeMillis());
        if (channelMapper.updateByPrimaryKeySelective(channel) == 1) {
            // 修改成功之后删除redis缓存中的 channels 集合
            redisTemplate.delete("usableChannels");
            return Result.ok(ResultCode.UPDATE_SUCCESS);
        }
        return Result.error(ResultCode.UPDATE_ERROR);
    }

    /**
     * 查找未被禁用的频道
     *
     * @return
     */
    @Override
    public Result findUsableChannel() {
        // 先查询 redis缓存 中是否存在 channels 集合
        List<Channel> channels = redisTemplate.opsForList().range("usableChannels", 0, -1);
        if (channels == null || channels.size() == 0) {
            channels = channelMapper.findUsableChannel();
            redisTemplate.opsForList().rightPushAll("usableChannels", channels);
        }
        return Result.ok().data("usable_channels", channels);
    }

    /**
     * 删除频道
     *
     * @param channelId
     * @return
     */
    @Override
    public Result deleteChannel(Integer channelId) {
        if (channelMapper.deleteByPrimaryKey(channelId) == 1) {
            // 删除成功之后删除redis缓存中的 channels 集合
            redisTemplate.delete("usableChannels");
            return Result.ok(ResultCode.DELETE_SUCCESS);
        }
        return Result.error(ResultCode.DELETE_ERROR);
    }
}
