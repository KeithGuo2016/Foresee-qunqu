package com.foresee.service;

import java.util.Date;
import java.util.List;

import com.foresee.baseService.BasicsSvc;
import com.foresee.pojo.ArticlesComment;
import com.foresee.vo.ArticlesCommentVo;
import com.github.pagehelper.PageInfo;

public interface ArticlesCommentService extends BasicsSvc<ArticlesComment>{
	public PageInfo<ArticlesCommentVo> selectCommentList(int articlesId,int page,int pageSize);
	public PageInfo<ArticlesCommentVo> selectToCommentList(int pid);
	ArticlesCommentVo selectCommentByid(int id);
	List<ArticlesCommentVo> selectToComment(int pid);
	List<ArticlesCommentVo>  selectCommentByDate(int userid,int articlesId,String content);
	
	public PageInfo<ArticlesCommentVo> selectMyComment(int userid,int page,int pageSize);
}
