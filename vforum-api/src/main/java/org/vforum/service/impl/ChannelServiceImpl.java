package org.vforum.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.vforum.common.entity.Result;
import org.vforum.common.entity.ResultCode;
import org.vforum.mapper.ChannelMapper;
import org.vforum.mapper.ColumnMapper;
import org.vforum.model.entity.Channel;
import org.vforum.model.entity.Column;
import org.vforum.model.vo.ChannelColumnVo;
import org.vforum.service.ChannelService;

import java.util.ArrayList;
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
    private ColumnMapper columnMapper;

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
            redisTemplate.delete("channel_column");
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
            redisTemplate.delete("channel_column");
            return Result.ok(ResultCode.UPDATE_SUCCESS);
        }
        return Result.error(ResultCode.UPDATE_ERROR);
    }

    /**
     * 查找未被禁用的频道和专栏（树形结构）
     *
     * @return
     */
    @Override
    public Result findUsableChannelAndColumnByTree() {
        // 先查询 redis缓存 中是否存在 channels 集合
        List<ChannelColumnVo> channelColumn = redisTemplate.opsForList().range("channel_column", 0, -1);
        if (channelColumn == null || channelColumn.size() == 0) {
            // 获取所有的频道和所有的专栏
            List<Channel> channels = channelMapper.findUsableChannel();
            List<Column> columns = columnMapper.findUsableColumns();
            List<ChannelColumnVo> tree = new ArrayList<>();
            // 遍历所有的频道，将频道和专栏转为树形结构
            channels.forEach(channel -> {
                ChannelColumnVo vo = new ChannelColumnVo();
                // 深拷贝
                BeanUtils.copyProperties(channel, vo);
                List<Column> list = new ArrayList<>();
                columns.forEach(column -> {
                    if (column.getChannelId() == channel.getId()) {
                        list.add(column);
                    }
                    vo.setChildren(list);
                });

                tree.add(vo);
            });
            // 将数据存储到 redis，再返回
            redisTemplate.opsForList().rightPushAll("channel_column", tree);
            return Result.ok().data("channel_column", tree);
        }
        return Result.ok().data("channel_column", channelColumn);
    }

    /**
     * 删除频道
     *
     * @param channelId
     * @return
     */
    @Override
    public Result deleteChannel(Integer channelId) {
        // 删除之前，先查看这个频道下面是否有专栏
        List<Column> columns = columnMapper.findColumnByChannelId(channelId);
        if (columns != null || columns.size() != 0) {
            return Result.error(ResultCode.CHANNEL_HAS_COLUMN);
        }

        if (channelMapper.deleteByPrimaryKey(channelId) == 1) {
            // 删除成功之后删除redis缓存中的 channels 集合
            redisTemplate.delete("channel_column");
            return Result.ok(ResultCode.DELETE_SUCCESS);
        }
        return Result.error(ResultCode.DELETE_ERROR);
    }
}
