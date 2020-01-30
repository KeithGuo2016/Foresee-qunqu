package com.foresee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.dao.UserGatherMapper;
import com.foresee.model.UserGather;
import com.foresee.service.UserGatherService;
@Service
@Transactional(rollbackFor = Exception.class)
public class UserGatherServiceImpl implements UserGatherService{

	@Autowired UserGatherMapper userGatherDao;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userGatherDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserGather record) {
		// TODO Auto-generated method stub
		return userGatherDao.insert(record);
	}

	@Override
	public int insertSelective(UserGather record) {
		// TODO Auto-generated method stub
		return userGatherDao.insertSelective(record);
	}

	@Override
	public UserGather selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userGatherDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserGather record) {
		// TODO Auto-generated method stub
		return userGatherDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserGather record) {
		// TODO Auto-generated method stub
		return userGatherDao.updateByPrimaryKey(record);
	}
}