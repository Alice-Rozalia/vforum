package org.vforum.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/24 10:55
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {

    private Long total;

    private List<T> rows;
}
