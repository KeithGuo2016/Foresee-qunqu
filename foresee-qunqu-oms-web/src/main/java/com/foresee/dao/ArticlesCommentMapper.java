package com.foresee.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.foresee.model.ArticlesComment;
/**
 * @version 1.0
 * @author wrh
 */
@Repository
public interface ArticlesCommentMapper {
    
    ArticlesComment getById(Integer id);

    List<ArticlesComment> listPage(ArticlesComment articlesComment);
    
    int insert(ArticlesComment articlesComment);
    
    int update(ArticlesComment articlesComment);
    
    int deleteById(Integer id);
}