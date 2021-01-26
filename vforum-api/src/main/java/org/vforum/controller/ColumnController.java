package org.vforum.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vforum.common.entity.Result;
import org.vforum.model.entity.Column;
import org.vforum.service.ColumnService;

import javax.validation.Valid;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/25 18:27
 */
@RestController
@RequestMapping("/api/v1")
@Api(value = "系统管理模块", tags = "专栏管理")
public class ColumnController {

    @Autowired
    private ColumnService columnService;

    @GetMapping("/pri/column")
    @ApiOperation(value = "所有专栏", notes = "获取所有的专栏")
    public Result findAllColumnList() {
        return columnService.findAllColumnList();
    }

    @GetMapping("/pub/column/{columnId}")
    @ApiOperation(value = "单个专栏", notes = "根据id查找专栏")
    public Result findColumnById(@PathVariable Integer columnId) {
        return columnService.findColumnById(columnId);
    }

    @PostMapping("/pri/column")
    @ApiOperation(value = "添加专栏", notes = "添加专栏")
    public Result addColumn(@RequestBody @Valid Column column) {
        return columnService.addColumn(column);
    }

    @PutMapping("/pri/column")
    @ApiOperation(value = "修改专栏", notes = "修改专栏")
    public Result updateColumn(@RequestBody @Valid Column column) {
        return columnService.updateColumn(column);
    }

    @DeleteMapping("/pri/column/{columnId}")
    @ApiOperation(value = "删除专栏", notes = "删除专栏")
    public Result deleteColumnById(@PathVariable Integer columnId) {
        return columnService.deleteColumnById(columnId);
    }
}
