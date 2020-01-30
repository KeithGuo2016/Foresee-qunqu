package com.foresee.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.baseService.impl.BasicsSvcImpl;
import com.foresee.mapper.ArticlesCommentMapperCostom;
import com.foresee.pojo.ArticlesComment;
import com.foresee.service.ArticlesCommentService;
import com.foresee.vo.ArticlesCommentVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ArticlesCommentServiceImpl extends BasicsSvcImpl<ArticlesComment> implements ArticlesCommentService{
	
	@Autowired
	private ArticlesCommentMapperCostom costom;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public PageInfo<ArticlesCommentVo> selectCommentList(int articlesId,int page,int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ArticlesCommentVo> list = costom.selectCommentList(articlesId);
		PageInfo<ArticlesCommentVo> pageInfo = new PageInfo<ArticlesCommentVo>(list);
		return pageInfo;
		
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public PageInfo<ArticlesCommentVo> selectToCommentList(int pid) {
		
		PageHelper.startPage(1, 2);
		List<ArticlesCommentVo> list = costom.selectToCommentList(pid);
		PageInfo<ArticlesCommentVo> pageInfo = new PageInfo<ArticlesCommentVo>(list);
		return pageInfo;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ArticlesCommentVo> selectToComment(int pid) {

		return costom.selectToCommentList(pid);
	}


	@Override
	public ArticlesCommentVo selectCommentByid(int id) {
		return costom.selectCommentByid(id);
	}

	@Override
	public List<ArticlesCommentVo> selectCommentByDate(int userid, int articlesId, String content) {
		
		return costom.selectCommentByDate(userid, articlesId, content);
	}

	@Override
	public PageInfo<ArticlesCommentVo> selectMyComment(int userid, int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ArticlesCommentVo> list = costom.selectMyComment(userid);
		PageInfo<ArticlesCommentVo> pageInfo = new PageInfo<ArticlesCommentVo>(list);
		return pageInfo;
	}

}
