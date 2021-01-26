package org.vforum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vforum.common.entity.Result;
import org.vforum.common.entity.ResultCode;
import org.vforum.mapper.ColumnMapper;
import org.vforum.model.entity.Column;
import org.vforum.service.ColumnService;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/25 18:32
 */
@Service
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    private ColumnMapper columnMapper;

    /**
     * 获取所有的专栏
     *
     * @return
     */
    @Override
    public Result findAllColumnList() {
        List<Column> columns = columnMapper.selectAll();
        return Result.ok().data("columns", columns);
    }

    /**
     * 根据id查找专栏
     *
     * @param columnId
     * @return
     */
    @Override
    public Result findColumnById(Integer columnId) {
        Column column = columnMapper.selectByPrimaryKey(columnId);
        return Result.ok().data("column", column);
    }

    /**
     * 添加专栏
     *
     * @param column
     * @return
     */
    @Override
    public Result addColumn(Column column) {
        column.setCreateTime(System.currentTimeMillis());
        column.setSubject(0);
        if (columnMapper.insertSelective(column) == 1) {
            return Result.ok(ResultCode.ADD_SUCCESS);
        }
        return Result.error(ResultCode.ADD_ERROR);
    }

    /**
     * 修改专栏
     *
     * @param column
     * @return
     */
    @Override
    public Result updateColumn(Column column) {
        if (columnMapper.insertSelective(column) == 1) {
            return Result.ok(ResultCode.UPDATE_SUCCESS);
        }
        return Result.error(ResultCode.UPDATE_ERROR);
    }

    /**
     * 删除专栏
     *
     * @param columnId
     * @return
     */
    @Override
    public Result deleteColumnById(Integer columnId) {
        if (columnMapper.deleteByPrimaryKey(columnId) == 1) {
            return Result.ok(ResultCode.DELETE_SUCCESS);
        }
        return Result.error(ResultCode.DELETE_ERROR);
    }
}
