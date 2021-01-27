package org.vforum.service;

import org.vforum.common.entity.Result;
import org.vforum.model.entity.Comment;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/26 22:53
 */
public interface CommentService {
    /**
     * 查询所有评论
     * @return
     */
    Result findAllComment();

    /**
     * 根据id获取评论
     * @param commentId
     * @return
     */
    Result findCommentById(String commentId);

    /**
     * 新增评论
     * @param comment
     * @return
     */
    Result addComment(Comment comment);

    /**
     * 修改评论
     * @param comment
     * @return
     */
    Result updateComment(Comment comment);

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    Result deleteCommentById(String commentId);

    /**
     * 根据文章id查询评论
     * @param articleId
     * @return
     */
    Result findCommentByArticleId(String articleId);
}
