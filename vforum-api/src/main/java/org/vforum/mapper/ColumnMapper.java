package org.vforum.mapper;

import org.apache.ibatis.annotations.Param;
import org.vforum.model.entity.Column;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/25 18:31
 */
public interface ColumnMapper extends Mapper<Column> {

    List<Column> findUsableColumns();

    List<Column> findColumnByChannelId(@Param("channelId") Integer id);
}
