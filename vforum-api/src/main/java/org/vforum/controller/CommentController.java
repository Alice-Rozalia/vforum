package org.vforum.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vforum.common.entity.Result;
import org.vforum.model.entity.Comment;
import org.vforum.service.CommentService;

import javax.validation.Valid;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/26 22:54
 */
@RestController
@RequestMapping("/api/v1")
@Api(value = "系统管理模块", tags = "评论管理")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/pri/comment")
    @ApiOperation(value = "所有评论", notes = "获取所有的评论")
    public Result findAllComment() {
        return commentService.findAllComment();
    }

    @GetMapping("/pri/comment/{commentId}")
    @ApiOperation(value = "单个评论", notes = "根据id获取评论")
    public Result findCommentById(@PathVariable String commentId) {
        return commentService.findCommentById(commentId);
    }

    @GetMapping("/pub/comment/article/{articleId}")
    @ApiOperation(value = "文章评论", notes = "根据文章id查询评论")
    public Result findCommentByArticleId(@PathVariable String articleId) {
        return commentService.findCommentByArticleId(articleId);
    }

    @PostMapping("/pri/comment")
    @ApiOperation(value = "新增评论", notes = "新增评论")
    public Result addComment(@RequestBody @Valid Comment comment) {
        return commentService.addComment(comment);
    }

    @PutMapping("/pri/comment")
    @ApiOperation(value = "修改评论", notes = "修改评论")
    public Result updateComment(@RequestBody @Valid Comment comment) {
        return commentService.updateComment(comment);
    }

    @DeleteMapping("/pri/comment/{commentId}")
    @ApiOperation(value = "删除评论", notes = "根据id删除评论")
    public Result deleteCommentById(@PathVariable String commentId) {
        return commentService.deleteCommentById(commentId);
    }
}
