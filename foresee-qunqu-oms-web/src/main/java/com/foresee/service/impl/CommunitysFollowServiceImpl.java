package com.foresee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.dao.CommunitysFollowMapper;
import com.foresee.model.CommunitysFollow;
import com.foresee.service.CommunitysFollowService;
@Service
@Transactional(rollbackFor = Exception.class)
public class CommunitysFollowServiceImpl implements CommunitysFollowService{

	@Autowired CommunitysFollowMapper communitysFollowDao;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return communitysFollowDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CommunitysFollow record) {
		// TODO Auto-generated method stub
		return communitysFollowDao.insert(record);
	}

	@Override
	public int insertSelective(CommunitysFollow record) {
		// TODO Auto-generated method stub
		return communitysFollowDao.insertSelective(record);
	}

	@Override
	public CommunitysFollow selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return communitysFollowDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CommunitysFollow record) {
		// TODO Auto-generated method stub
		return communitysFollowDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CommunitysFollow record) {
		// TODO Auto-generated method stub
		return communitysFollowDao.updateByPrimaryKey(record);
	}
}