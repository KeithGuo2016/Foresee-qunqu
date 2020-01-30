package com.foresee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.dao.CommunityFamilyMapper;
import com.foresee.model.CommunityFamily;
import com.foresee.service.CommunityFamilyService;
@Service
@Transactional(rollbackFor = Exception.class)
public class CommunityFamilyServiceImpl implements CommunityFamilyService{

	@Autowired CommunityFamilyMapper communityFamilyDao;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return communityFamilyDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CommunityFamily record) {
		// TODO Auto-generated method stub
		return communityFamilyDao.insert(record);
	}

	@Override
	public int insertSelective(CommunityFamily record) {
		// TODO Auto-generated method stub
		return communityFamilyDao.insertSelective(record);
	}

	@Override
	public CommunityFamily selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return communityFamilyDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CommunityFamily record) {
		// TODO Auto-generated method stub
		return communityFamilyDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CommunityFamily record) {
		// TODO Auto-generated method stub
		return communityFamilyDao.updateByPrimaryKey(record);
	}

	@Override
	public List<CommunityFamily> selectList(CommunityFamily record) {
		// TODO Auto-generated method stub
		return communityFamilyDao.selectList(record);
	}
}