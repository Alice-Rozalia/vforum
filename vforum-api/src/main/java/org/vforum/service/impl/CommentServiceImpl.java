package org.vforum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vforum.common.entity.Result;
import org.vforum.common.entity.ResultCode;
import org.vforum.model.entity.Comment;
import org.vforum.repository.CommentRepository;
import org.vforum.service.CommentService;
import org.vforum.utils.IdWorker;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/26 22:53
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * 查询所有评论
     *
     * @return
     */
    @Override
    public Result findAllComment() {
        List<Comment> all = commentRepository.findAllByOrderByPublishTimeDesc();
        return Result.ok().data("comments", all);
    }

    /**
     * 根据id获取评论
     *
     * @param commentId
     * @return
     */
    @Override
    public Result findCommentById(String commentId) {
        Optional<Comment> optional = commentRepository.findById(commentId);
        if (optional.isPresent()) {
            return Result.ok().data("comment", optional.get());
        }

        return Result.ok();
    }

    /**
     * 新增评论
     *
     * @param comment
     * @return
     */
    @Override
    public Result addComment(Comment comment) {
        comment.setThumbup(0);
        comment.setPublishTime(new Date());

        commentRepository.save(comment);
        return Result.error(ResultCode.ADD_SUCCESS);
    }

    /**
     * 修改评论
     *
     * @param comment
     * @return
     */
    @Override
    public Result updateComment(Comment comment) {
        // mongoDBRepository 中的 save 方法：如果主键不存在则新增，存在则是修改
        commentRepository.save(comment);
        return Result.ok(ResultCode.UPDATE_SUCCESS);
    }

    /**
     * 删除评论
     *
     * @param commentId
     * @return
     */
    @Override
    public Result deleteCommentById(String commentId) {
        commentRepository.deleteById(commentId);
        return Result.ok(ResultCode.DELETE_SUCCESS);
    }

    /**
     * 根据文章id查询评论
     *
     * @param articleId
     * @return
     */
    @Override
    public Result findCommentByArticleId(String articleId) {
        List<Comment> list = commentRepository.findByArticleIdOrderByPublishTimeDesc(articleId);
        return Result.ok().data("comments", list);
    }
}
