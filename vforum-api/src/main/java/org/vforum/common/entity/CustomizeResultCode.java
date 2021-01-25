package org.vforum.common.entity;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/23 13:35
 */
public interface CustomizeResultCode {
    /**
     * 获取错误状态码
     * @return 错误状态码
     */
    Integer getCode();

    /**
     * 获取错误信息
     * @return 错误信息
     */
    String getMessage();
}
