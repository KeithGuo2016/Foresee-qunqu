package com.foresee.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.dao.ArticlesMapper;
import com.foresee.dao.UserApplyMapper;
import com.foresee.exception.ResultException;
import com.foresee.model.Articles;
import com.foresee.model.User;
import com.foresee.model.UserApply;
import com.foresee.service.ArticlesService;
import com.foresee.service.UserNewsService;
import com.foresee.utils.ResultCode;
import com.foresee.utils.StringUtil;
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticlesServiceImpl implements ArticlesService{

	@Autowired ArticlesMapper articlesDao;
	@Autowired UserApplyMapper userApplyDao;
	@Autowired UserNewsService userNewsService;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articlesDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Articles record) {
		// TODO Auto-generated method stub
		return articlesDao.insert(record);
	}

	@Override
	public int insertSelective(Articles record,User user) {
		// TODO Auto-generated method stub
		int n = articlesDao.insertSelective(record);
		/*if("1".equals(record.getIsRecommend())){
			applyArticles(user, record.getId());
		}*/
		return n;
		
	}

	@Override
	public Articles selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articlesDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Articles record,User user) {
		// TODO Auto-generated method stub
		
		Articles articles = articlesDao.selectByPrimaryKey(record.getId());
		if(StringUtil.isNotNull(record.getFlowSts())){
			if(!articles.getFlowSts().equals(record.getFlowSts())){
				userNewsService.insert(user, Integer.parseInt(articles.getCreatedBy()), 7,Integer.parseInt(record.getFlowSts()) , record.getRecommendDateStr(), record.getId());
			}
		}
		return articlesDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Articles record) {
		// TODO Auto-generated method stub
		return articlesDao.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Articles record) {
		// TODO Auto-generated method stub
		return articlesDao.updateByPrimaryKey(record);
	}

	@Override
	public List<Articles> selectList(Articles record) {
		// TODO Auto-generated method stub
		return articlesDao.selectList(record);
	}

	@Override
	public boolean applyArticlesRecommend(User user, int id, String isRecommend) {
		// TODO Auto-generated method stub
		Articles articles = articlesDao.selectByPrimaryKey(id);
		articles.setFlowSts("1");
		articles.setIsRecommend(isRecommend);
		articles.setUpdatedDate(new Date());
		articles.setUpdatedBy(user.getTh1());
		return articlesDao.updateByPrimaryKeySelective(articles)>0;
	}

	@Override
	public boolean applyArticles(User user, int id) {
		// TODO Auto-generated method stub
		Articles articles = articlesDao.selectByPrimaryKey(id);
		if("1".equals(articles.getIsDraft())){
			throw new ResultException(ResultCode.ARTICLES_APPLY_ERROR);
		}
		if("1".equals(articles.getFlowSts()) || "3".equals(articles.getFlowSts()) || articles.getIsDeleted()==1){
			throw new ResultException(ResultCode.ARTICLES_ISRECOMMEND_APPLY_ERROR);
		}
		articles.setFlowSts("3");
		articles.setUpdatedDate(new Date());
		articles.setUpdatedBy(user.getTh1());
		
		UserApply userApply = new UserApply();
		userApply.setUserid(user.getId());
		userApply.setApplyType("7");
		userApply.setApplySts("0");
		userApply.setPhone(user.getUserphone());
		userApply.setCreatedDate(new Date());
		userApply.setCreatedBy(user.getTh1());
		userApply.setApplyTargetId(id);
		userApplyDao.insertSelective(userApply);
		return articlesDao.updateByPrimaryKeySelective(articles)>0;
	}
}