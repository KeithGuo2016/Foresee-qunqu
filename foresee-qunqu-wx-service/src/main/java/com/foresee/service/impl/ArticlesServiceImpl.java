package com.foresee.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.baseService.impl.BasicsSvcImpl;
import com.foresee.mapper.ArticlesMapper;
import com.foresee.mapper.ArticlesMapperCostom;
import com.foresee.pojo.Articles;
import com.foresee.service.ArticlesService;
import com.foresee.vo.ArticleVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ArticlesServiceImpl extends BasicsSvcImpl<Articles> implements ArticlesService{
	@Autowired
	private ArticlesMapper articlesMapper;
	
	@Autowired
	private ArticlesMapperCostom articlesCostom;
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS) // 设置事务
	public PageInfo<Articles>  selectRecommend(String  selectDesc,int page,int pageSize,String orderBy) {
		PageHelper.startPage(page, pageSize);
		
		List<Articles> list =articlesMapper.selectRecommend(selectDesc,orderBy);
		PageInfo<Articles> pageInfo = new PageInfo<Articles>(list);
		return pageInfo;
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS) // 设置事务
	public ArticleVo selectVoById(int id,String userid) {
		
		return articlesCostom.selectVoById(id,userid);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ArticleVo> selectHome() {
		
		return articlesCostom.selectHome();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ArticleVo> selectHomeByDate(Date dates) {
		
		return articlesCostom.selectHomeByDate(dates);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS) // 设置事务
	public PageInfo<ArticleVo> selectListByFollowUserId(int userid,int page,int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ArticleVo> list =articlesCostom.selectListByFollowUserId(userid);
		PageInfo<ArticleVo> pageInfo = new PageInfo<ArticleVo>(list);
		return pageInfo;
		
	}

	@Override
	public PageInfo<ArticleVo> selectRecommendList(int page, int pageSize) {
		
		PageHelper.startPage(page, pageSize);
		List<ArticleVo> list =articlesCostom.selectRecommendList();
		PageInfo<ArticleVo> pageInfo = new PageInfo<ArticleVo>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<ArticleVo> selectListByUserId(int userid, int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ArticleVo> list =articlesCostom.selectListByUserId(userid);
		PageInfo<ArticleVo> pageInfo = new PageInfo<ArticleVo>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<ArticleVo> searchRecommendArticles(String tagId, String searchDesc, int page, int pageSize) {
		
		PageHelper.startPage(page, pageSize);
		List<ArticleVo> list =articlesCostom.searchRecommendArticles(tagId, searchDesc);
		PageInfo<ArticleVo> pageInfo = new PageInfo<ArticleVo>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<ArticleVo> searchArticles(String tagId, String searchDesc, int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ArticleVo> list =articlesCostom.searchArticles(tagId, searchDesc);
		PageInfo<ArticleVo> pageInfo = new PageInfo<ArticleVo>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<ArticleVo> selectListByCommunityId(int communityid,int page,int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ArticleVo> list =articlesCostom.selectListByCommunityId(communityid);
		PageInfo<ArticleVo> pageInfo = new PageInfo<ArticleVo>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<ArticleVo> selectTempList(int userid, int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ArticleVo> list =articlesCostom.selectTempList(userid);
		PageInfo<ArticleVo> pageInfo = new PageInfo<ArticleVo>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<ArticleVo> selectByGetherList(int userid,int gatherId, int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ArticleVo> list =articlesCostom.selectByGetherList(userid,gatherId);
		PageInfo<ArticleVo> pageInfo = new PageInfo<ArticleVo>(list);
		return pageInfo;
	}

	@Override
	public void updateById(Articles articles) {
		articlesMapper.updateById(articles);
	}

	@Override
	public PageInfo<ArticleVo> selectNotGether(int userid, int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ArticleVo> list =articlesCostom.selectNotGether(userid);
		PageInfo<ArticleVo> pageInfo = new PageInfo<ArticleVo>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<ArticleVo> selectGetherDetail(int gatherId, int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ArticleVo> list =articlesCostom.selectGetherDetail(gatherId);
		PageInfo<ArticleVo> pageInfo = new PageInfo<ArticleVo>(list);
		return pageInfo;
	}
}
