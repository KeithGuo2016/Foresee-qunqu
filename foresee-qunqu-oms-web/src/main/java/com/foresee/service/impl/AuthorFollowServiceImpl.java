package com.foresee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.dao.AuthorFollowMapper;
import com.foresee.model.AuthorFollow;
import com.foresee.service.AuthorFollowService;
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthorFollowServiceImpl implements AuthorFollowService{

	@Autowired AuthorFollowMapper authorFollowDao;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return authorFollowDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AuthorFollow record) {
		// TODO Auto-generated method stub
		return authorFollowDao.insert(record);
	}

	@Override
	public int insertSelective(AuthorFollow record) {
		// TODO Auto-generated method stub
		return authorFollowDao.insertSelective(record);
	}

	@Override
	public AuthorFollow selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return authorFollowDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AuthorFollow record) {
		// TODO Auto-generated method stub
		return authorFollowDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AuthorFollow record) {
		// TODO Auto-generated method stub
		return authorFollowDao.updateByPrimaryKey(record);
	}
}