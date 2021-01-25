package org.vforum.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vforum.common.entity.Result;
import org.vforum.model.entity.Channel;
import org.vforum.service.ChannelService;

import javax.validation.Valid;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/25 23:33
 */
@RestController
@RequestMapping("/api/v1")
@Api(value = "系统管理模块", tags = "频道管理")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @GetMapping("/pri/channel/all")
    @ApiOperation(value = "所有频道", notes = "获取所有的频道")
    public Result findAllChannelList() {
        return channelService.findAllChannelList();
    }

    @GetMapping("/pub/channel")
    @ApiOperation(value = "可用频道", notes = "获取所有未被禁用的频道")
    public Result findUsableChannel() {
        return channelService.findUsableChannel();
    }

    @PostMapping("/pri/channel")
    @ApiOperation(value = "添加频道", notes = "添加频道")
    public Result addChannel(@RequestBody @Valid Channel channel) {
        return channelService.addChannel(channel);
    }

    @PutMapping("/pri/channel")
    @ApiOperation(value = "修改频道", notes = "修改频道")
    public Result updateChannel(@RequestBody @Valid Channel channel) {
        return channelService.updateChannel(channel);
    }

    @DeleteMapping("/pri/channel/{channelId}")
    @ApiOperation(value = "删除频道", notes = "删除频道")
    public Result deleteChannel(@PathVariable Integer channelId) {
        return channelService.deleteChannel(channelId);
    }
}
