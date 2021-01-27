package org.vforum.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.vforum.model.entity.Comment;

import java.util.List;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/26 22:50
 */
public interface CommentRepository extends MongoRepository<Comment, String> {

    // Spring Data MongoDB，支持通过查询方法名进行查询定义的方式
    List<Comment> findAllByOrderByPublishTimeDesc();

    List<Comment> findByArticleIdOrderByPublishTimeDesc(String articleId);
}
