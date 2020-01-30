package com.foresee.service;

import java.util.List;

import com.foresee.model.ArticlesComment;

/**
 * @version 1.0
 * @author wrh
 */
public interface ArticlesCommentService {
	
	public int add(ArticlesComment articlesComment);

	public int update(ArticlesComment articlesComment);
    
	public int delete(Integer id);

	public ArticlesComment getById(Integer id);

	public List<ArticlesComment> listPage(ArticlesComment articlesComment);

}
