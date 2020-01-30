package com.foresee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.dao.ArticlesFollowMapper;
import com.foresee.model.ArticlesFollow;
import com.foresee.service.ArticlesFollowService;
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticlesFollowServiceImpl implements ArticlesFollowService {
	@Autowired ArticlesFollowMapper articlesFollowDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articlesFollowDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ArticlesFollow record) {
		// TODO Auto-generated method stub
		return articlesFollowDao.insert(record);
	}

	@Override
	public int insertSelective(ArticlesFollow record) {
		// TODO Auto-generated method stub
		return articlesFollowDao.insertSelective(record);
	}

	@Override
	public ArticlesFollow selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articlesFollowDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ArticlesFollow record) {
		// TODO Auto-generated method stub
		return articlesFollowDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ArticlesFollow record) {
		// TODO Auto-generated method stub
		return articlesFollowDao.updateByPrimaryKey(record);
	}
}