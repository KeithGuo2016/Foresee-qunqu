package com.foresee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.service.ArticlesCommentService;
import com.foresee.dao.ArticlesCommentMapper;
import com.foresee.model.ArticlesComment;

/**
 * @version 1.0
 * @author wrh
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticlesCommentServiceImpl implements ArticlesCommentService {
	
    @Autowired private ArticlesCommentMapper articlesCommentMapper;
    
	public int add(ArticlesComment articlesComment) {
		if(null == articlesComment){
			return 0;
		}
        int rows = articlesCommentMapper.insert(articlesComment);
        return rows;
	}

    
    public int update(ArticlesComment articlesComment) {
		if(null == articlesComment){
			return 0;
		}
        int rows = articlesCommentMapper.update(articlesComment);
        return rows;
    }
    
    
    public int delete(Integer id) {
		if(null == id){
			return 0;
		}
		ArticlesComment articlesComment =  new ArticlesComment();
		articlesComment.setPid(id);
		articlesComment.setOrderBy(1);
		List<ArticlesComment> listPage = articlesCommentMapper.listPage(articlesComment);
		for(int i=0;i<listPage.size();i++){
			articlesCommentMapper.deleteById(listPage.get(i).getId());
		}
        int rows = articlesCommentMapper.deleteById(id);
        return rows;
    }
    
    
    public ArticlesComment getById(Integer id) {
		if(null == id){
			return null;
		}
		ArticlesComment articlesComment = articlesCommentMapper.getById(id);
        return articlesComment;
    }
    
	public List<ArticlesComment> listPage(ArticlesComment articlesComment){
		List<ArticlesComment> lists = articlesCommentMapper.listPage(articlesComment);
		return lists;
	}
}
