package org.vforum.service;

import org.vforum.common.entity.Result;
import org.vforum.model.entity.Column;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/25 18:31
 */
public interface ColumnService {

    /**
     * 获取所有的专栏
     * @return
     */
    Result findAllColumnList();

    /**
     * 根据id查找专栏
     * @param columnId
     * @return
     */
    Result findColumnById(Integer columnId);

    /**
     * 添加专栏
     * @param column
     * @return
     */
    Result addColumn(Column column);
}
